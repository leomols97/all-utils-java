#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/sem.h>
#include <sys/types.h>

int opsem(int sem, int r)
{
    int n;
    struct sembuf op[1];
    op[0].sem_num = 0; // un seul sémaphore dans l'ensemble
    op[0].sem_op = r;
    op[0].sem_flg = 0;
    if ((n = semop(sem, op, 1)) < 0)
    {
        fprintf(stderr, "opsem : err semop\n");
        exit(1);
    }
    return (n);
}

int main(void)
{
    int tete, vides, pleines, shm, semt;
    unsigned char *t; // tableau partagé

    tete = 0;
    if ((shm = shmget(11, 5, 0777 | IPC_CREAT)) < 0)
    {
        perror("shmget");
        exit(201);
    }
    t = shmat(shm, 0, 0777);
    if ((semt = semget(41, 1, 0666 | IPC_CREAT)) < 0)
    {
        perror("semget plein");
        exit(204);
    }
    if ((pleines = semget(21, 1, 0666 | IPC_CREAT)) < 0)
    {
        perror("semget plein");
        exit(202);
    }
    if ((vides = semget(31, 1, 0666 | IPC_CREAT)) < 0)
    {
        perror("semget plein");
        exit(203);
    }

    int status = 0;
    while (status != -1) // suppression du sémaphore ?
    {
        if ((status = opsem(pleines, -1)) != -1)
        { // down pleines

            opsem(semt, -1);
            write(1, t + tete, 1);     // Afficher le caractère en tête
            tete = (tete + 1) % 5;     // chaise musicale
            status = opsem(vides, +1); // up vides, une nouvelle case vide
            opsem(semt, 1);
        }
    }
    shmdt(t);
    exit(0);
}

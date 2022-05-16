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
    int queue, vides, pleines, shm, semt;
    unsigned char *t; // tableau partagé
    char b;
    queue = 0;
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

    while (read(0, &b, 1) > 0)
    {
        opsem(vides, -1); // down vide
        t[queue] = b;
        queue = (queue + 1) % 5;
        opsem(pleines, +1); // up  plein
    }

    exit(0);
}

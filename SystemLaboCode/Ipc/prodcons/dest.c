#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/sem.h>
#include <sys/types.h>

int main(void)
{
    int vide, plein, semt, shm;
    if ((semt = semget(41, 1, 0666 | IPC_CREAT)) < 0)
    {
        perror("semget plein");
        exit(204);
    }
    if ((plein = semget(21, 1, 0666 | IPC_CREAT)) < 0)
    {
        perror("semget plein");
        exit(202);
    }
    if ((vide = semget(31, 1, 0666 | IPC_CREAT)) < 0)
    {
        perror("semget plein");
        exit(203);
    }
    if ((shm = shmget(11, 5, 0777 | IPC_CREAT)) < 0)
    {
        perror("shmget");
        exit(201);
    }
    shmctl(shm, IPC_RMID, 0);
    semctl(plein, 0, IPC_RMID, 0);
    semctl(vide, 0, IPC_RMID, 0);
    semctl(semt, 0, IPC_RMID, 0);
    exit(0);
}
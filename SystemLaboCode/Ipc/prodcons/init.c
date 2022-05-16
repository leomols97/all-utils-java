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
    if (semctl(plein, 0, SETVAL, 0) < 0)
    {
        perror("semctl plein");
        exit(204);
    }
    if (semctl(vide, 0, SETVAL, 5) < 0)
    {
        perror("semctl vide");
        exit(205);
    }
    if (semctl(semt, 0, SETVAL, 1) < 0)
    {
        perror("semctl vide");
        exit(203);
    }
    exit(0);
}

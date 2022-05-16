#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/sem.h>
#include <sys/types.h>

int main(void)
{
        int semc;
        if((semc = semget(40, 1, 0666 | IPC_CREAT)) < 0)
        {
                perror("semget plein");
                exit(200);
        }

        int n = (rand() % (2000 - 1000 + 1)) + 1000;

        if(semctl(semc, 0, SETVAL, n) < 0)
        {
                perror("semctl plein");
                exit(201);
        }

        exit(0);
}

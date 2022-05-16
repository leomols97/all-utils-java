#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/sem.h>
#include <sys/types.h>

int main(void)
{
        int sem;
        if((sem = semget(41, 1, 0666 | IPC_CREAT)) < 0)
        {
                perror("semget plein");
                exit(200);
        }

        if(semctl(sem, 0, SETVAL, 1) < 0)
        {
                perror("semctl plein");
                exit(201);
        }

        exit(0);
}


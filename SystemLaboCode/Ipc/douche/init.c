#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include<sys/types.h>
#include<sys/ipc.h>
#include<sys/sem.h>
#include<sys/shm.h>
#include<sys/wait.h>
#include <stdlib.h>
#include <unistd.h>

int main(void)
{
        int sem1,sem2,r;
        if (( sem1=semget(100,1,0666|IPC_CREAT))==-1) // sem garcon
        {
                perror ("semget");
                exit(-1);
        }

        if (( sem2=semget(101,1,0666|IPC_CREAT))==-1) // sem fille
        {
                perror ("semget");
                exit(-1);
        }

        if (semctl(sem1,0,SETVAL,0)==-1) // init sem garcon
        {       perror ("semctl");
                exit(1);
        }

        if (semctl(sem2,0,SETVAL,0)==-1) // init sem fille
        {       perror ("semctl");
                exit(1);
        }
        exit(0);
}

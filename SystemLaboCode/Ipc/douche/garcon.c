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
#include <signal.h>

int sem2, sem1;
struct sigaction act;

int opsem(int sem, int i)
{
        int n; struct sembuf op[1];
        op[0].sem_num = 0; // premier et unique sémaphore
        op[0].sem_op = i;
        op[0].sem_flg = SEM_UNDO;
        if ((n=semop(sem,op,1))==-1)
        {       perror ("semop");
                exit(1);
        }
}

void leave(int s)
{
        opsem(sem1, -1);
        exit(0);
}

int main(int argc, char* argv[])
{
        char c = *(argv[1]);
        act.sa_handler = leave;
        sigaction(SIGINT, &act, NULL);

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

        opsem(sem2, 0);
        opsem(sem1, 1);
        while(1)
        {
                write(1, &c, 1);
                sleep(2);
        }
        exit(0);
}
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

int opsem(int sem, int i)
{       int n; struct sembuf op[1];
        op[0].sem_num = 0; // premier et unique sémaphore
        op[0].sem_op = i;
        op[0].sem_flg = SEM_UNDO;
        if ((n=semop(sem,op,1))==-1)
        {       perror ("semop");
                exit(1);
        }
        return(n);
}

int main(void)
{
        int n,sem,r;
        int shm;
        char *c;
        if (( sem=semget(123,1,0666|IPC_CREAT))==-1) // chope le sémaphore
        {
                perror ("semget");
                exit(-1);
        }

        if ((shm = shmget (256, 1, 0666|IPC_CREAT)) == -1) // chope la memoire partage
        {
                perror ("shmget");
                exit(-1);
        }

        c = shmat (shm, NULL, 0666); // s'attacher a la memoire partage

        if (c == NULL)
        {
                perror ("shmat");
                exit (3);
        }

        opsem (sem, 0); // zero pour comme plusieur afficheur possible
        while (*c != 'q') {write(1,c,1);usleep(10000);}
        shmdt (c);
        exit(0);
}

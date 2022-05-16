#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/sem.h>
#include <sys/types.h>
#include <sys/wait.h>

int opsem(int sem, int i)
{
        struct sembuf p[1];
        int n;
        p[0].sem_num = 0;
        p[0].sem_op = i;
        p[0].sem_flg = IPC_NOWAIT;
        if((n = semop(sem, p, 1)) < 0)
        {
                printf("adios\n");
                exit(0);
        }
        return n;
}

int main(void)
{
        int semc;
        if((semc = semget(40, 1, 0666 | IPC_CREAT)) < 0)
        {
                perror("semget plein");
                exit(200);
        }

        if(fork() == 0)
        {
                while(1)
                {
                        int M = (rand() % (200 - 100 + 1)) + 100;
                        opsem(semc,-M);
                        printf("je suis process %d, je vole %d cacahuetes, je pars\n", getpid(), M);
                        sleep(5);
                }
        }

        if(fork() == 0)
        {
                while(1)
                {
                        int M = (rand() % (200 - 100 + 1)) + 100;
                        opsem(semc, -M);
                        printf("je suis process %d, je vole %d cacahuetes, je pars\n", getpid(), M);
                        sleep(4);
                }
        }

        if(fork() == 0)
        {
                while(1)
                {
                        int M = (rand() % (200 - 100 + 1)) + 100;
                        opsem(semc, -M);
                        printf("je suis process %d, je vole %d cacahuetes, je pars\n", getpid(), M);
                        sleep(3);
                }
        }

        while(wait(0) > 0);
        semctl(semc, 0, IPC_RMID, 0);
        exit(0);
}

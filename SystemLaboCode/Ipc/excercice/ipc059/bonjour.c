#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/sem.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>

int sema, semb;
struct sigaction act;

int opsem(int sem, int i)
{
        struct sembuf p[1];
        int n;
        p[0].sem_num = 0;
        p[0].sem_op = i;
        p[0].sem_flg = 0;
        if((n = semop(sem, p, 1)) < 0)
        {
                printf("semop");
                exit(1);
        }
        return n;
}

void bonjour(int s)
{
        opsem(sema, -1);
        printf("Bonjour ");
        fflush(stdout);
        opsem(semb, 1);
}

int main(void)
{
        if((sema = semget(40, 1, 0666 | IPC_CREAT)) < 0)
        {
                perror("semget plein");
                exit(200);
        }

        if((semb = semget(41, 1, 0666 | IPC_CREAT)) < 0)
        {
                perror("semget plein");
                exit(200);
        }

        act.sa_handler = bonjour;
        sigaction(SIGUSR1, &act, NULL);

        while(1) pause();

        exit(0);
}
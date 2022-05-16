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
        p[0].sem_flg = 0;
        if((n = semop(sem, p, 1)) < 0)
        {
                perror("semop");
                exit(1);
        }
        return n;
}

int main(void)
{
        int sema, semb;
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

        while(1)
        {
                opsem(semb, -1);
                printf("B");
                opsem(sema, 1);
        }

        exit(0);
}

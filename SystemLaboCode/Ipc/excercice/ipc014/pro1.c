#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/sem.h>
#include <sys/types.h>

int opsem(int sem, int r)
{
        int n;
        struct sembuf op[1];
        op[0].sem_num = 0;
        op[0].sem_op = r;
        op[0].sem_flg = 0;
        if((n = semop(sem, op, 1) < 0))
        {
                fprintf(stderr, "opsem : err semop");
                exit(1);
        }
        return n;
}

int main(void)
{
        int sem;
        if((sem = semget(41, 1, 0666 | IPC_CREAT)) < 0)
        {
                perror("semget plein");
                exit(200);
        }

        while(1)
        {
                opsem(sem, -1);
                printf("1\n");
                printf("1\n");
                opsem(sem, 1);
                sleep(2);
        }

        exit(0);
}

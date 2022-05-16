#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/sem.h>
#include <sys/types.h>

int opsem(int sem, int i)
{
        struct sembuf p[1];
        int n;
        p[0].sem_num = 0;
        p[0].sem_op = i;
        p[0].sem_flg = 0;
        if((n = semop(sem, p, 1) < 0))
        {
                fprintf(stderr, "opsem : err semop");
                exit(1);
        }
        return n;
}

int main(void)
{
        int sem;
        if((sem = semget(40, 1, 0666 | IPC_CREAT)) < 0)
        {
                perror("semget plein");
                exit(200);
        }

        printf("ici process 1 : je fait des trucs\n");
        sleep(10);
        printf("ici process 1 : trucs fini\n");
        opsem(sem, -1);
        exit(0);
}

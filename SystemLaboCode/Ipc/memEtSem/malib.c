#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

int creeSem()
{
        int sem;
        if((sem = semget(IPC_PRIVATE, 1, 0666 | IPC_CREAT)) == -1)
        {
                perror("semget");
                exit(-1);
        }
        return sem;
}

void initsem(int sem, int val)
{
        if(semctl(sem, 0, SETVAL, val) == -1)
        {
                perror("semctl");
                exit(-1);
        }
}

void supsem(int sem)
{
        if(semctl(sem, 0, IPC_RMID) != 0)
        {
                perror("semctl");
                exit(-1);
        }
}

void down(int sem)
{
        int n;
        struct sembuf op[1];
        op[0].sem_num = 0;
        op[0].sem_op = -1;
        op[0].sem_flg = SEM_UNDO;
        if((n = semop(sem, op, 1)) == -1)
        {
                perror("semop");
                exit(-1);
        }
}

void up(int sem)
{
        int n;
        struct sembuf op[1];
        op[0].sem_num = 0;
        op[0].sem_op = 1;
        op[0].sem_flg = SEM_UNDO;
        if((n = semop(sem, op, 1)) == -1)
        {
                perror("semop");
                exit(-1);
        }
}

void zero(int sem)
{
        int n;
        struct sembuf op[1];
        op[0].sem_num = 0;
        op[0].sem_op = 0;
        op[0].sem_flg = SEM_UNDO;
        if((n = semop(sem, op, 1)) == -1)
        {
                perror("semop");
                exit(-1);
        }
}

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
struct sigaction act2;

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

void monde(int s)
{
        opsem(semb, -1);
        printf("le monde\n");
        opsem(sema, 1);
}

// cette version du code c'est juste deux programme en un si vous avez la flemme de lancer 3 terminals.
// La, vous en lancerez que 2 terminals.
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

        if(fork() == 0)
        {
                act2.sa_handler = monde;
                sigaction(SIGUSR1, &act2, NULL);
                while(1) pause();
        }

        act.sa_handler = bonjour;
        sigaction(SIGUSR1, &act, NULL);

        while(1) pause();

        exit(0);
}

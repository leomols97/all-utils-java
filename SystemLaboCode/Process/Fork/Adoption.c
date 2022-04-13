#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main()
{
        int r;
        if((r=fork()) == 0)
        {
                printf("Je suis fils %d et mon père est %d \n", getpid(), getppid());
                printf("Go bedge\n");
                sleep(10);
                printf("Maintenant mon père c'est %d\n", getppid());
                exit(0);
        }

        printf("Je suis père %d\n", getpid());
        sleep(1);
        exit(0);
}
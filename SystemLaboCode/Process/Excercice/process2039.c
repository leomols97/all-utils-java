#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <fcntl.h>
#include <signal.h>
#include <errno.h>
#include <string.h>

int i = 0;
struct sigaction act;

void greeting(int s)
{
        if(i < 10)
        {
                printf("Bonjour %d\n", i);
                i++;
                alarm(3);
        }else
        {
                exit(0);
        }
}

int main(void)
{
        act.sa_handler = greeting;
        sigaction(SIGALRM, &act, NULL);
        alarm(3);
        while(1) pause();
        exit(0);
}

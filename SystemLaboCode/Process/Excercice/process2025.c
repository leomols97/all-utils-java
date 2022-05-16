#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <fcntl.h>
#include <signal.h>

struct sigaction act1;
struct sigaction act2;
struct sigaction act3;

void trapC(int s)
{
        printf("\nCa essaye de me tuer la >_< : %d\n", s);
}

void trapChild(int s)
{
        printf("Mon fils est mort, rip : %d\n", s);
}

void stop(int s)
{
        printf("\nhe he he stop : %d\n", s);
}

int main(void)
{
        act1.sa_handler = trapChild;
        act2.sa_handler = trapC;
        act3.sa_handler = stop;
        sigaction(SIGINT, &act2, NULL);
        sigaction(SIGCHLD, &act1, NULL);
        sigaction(SIGTSTP, &act3, NULL);
        if(fork() == 0)
        {
                sleep(5);
                exit(0);
        }
        while(1) pause();
        exit(0);
}
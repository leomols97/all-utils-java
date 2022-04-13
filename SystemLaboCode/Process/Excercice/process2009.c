#include <signal.h>
#include <stdlib.h>
#include <stdio.h>

void trapc(int sig);
static int cpt;
static char lettre;
static struct sigaction act;
static struct sigaction oldact;

int main (int argc, char * argv[])
{
        int temps = atoi(argv[1]);
        act.sa_handler = trapc;
        sigaction(SIGALRM, &act, NULL);
        alarm(temps);
        pause();
        //printf("wow");
        exit(0);
}

void trapc(int sig)
{
        //printf("wokge");
}

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
        act.sa_handler = trapc;
        sigaction (SIGINT,&act,&oldact);
        for (cpt=0;cpt>=0;cpt++)
        {       lettre='A'+(cpt%26);
                write(1,&lettre,1);
                sleep(1);
        }
        exit(0);
}

void trapc(int sig)
{
        sigaction(SIGINT, &oldact, NULL);
}

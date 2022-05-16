#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <fcntl.h>
#include <signal.h>
#include <errno.h>
#include <string.h>

void trapall(int sig, siginfo_t * pinfo, void * pucontext);
struct sigaction act;

int main (int argc, char * argv[])
{
        int noSig;
        int retour;
        char errmessage [256];

        printf ("Bonjour, je suis %d\n", getpid());
        act.sa_flags = SA_SIGINFO;
        act.sa_sigaction = trapall;

        for (noSig=1; noSig<32; noSig++){
                char * err;
                if ((retour = sigaction (noSig,&act,NULL)) < 0) {
                        printf("sig : %d\n", noSig);
                }
        }

        if(fork() == 0)
        {
                for(int i = 1; i < 32; i++)
                {
                        if(i != 9 && i != 19)
                                kill(getppid(), i);
                }
                exit(0);
        }
        sleep(100000);
        while (1) pause();
}

void trapall(int sig, siginfo_t * pinfo, void * pucontext){
        printf("reçu le signal=%d\n", pinfo->si_signo);
}


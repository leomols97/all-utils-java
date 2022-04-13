#include <errno.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define __USE_POSIX
#define __USE_POSIX199309
#include <signal.h>

void trapall(int sig, siginfo_t * pinfo, void * pucontext);
struct sigaction act;
struct sigaction newact;

int main (int argc, char * argv[])
{
        int noSig;
        int retour;
        char errmessage [256];

        printf ("Bonjour, je suis %d\n", getpid());
        act.sa_flags = SA_SIGINFO;
        act.sa_sigaction = trapall;
        newact.sa_handler = SIG_IGN;

        for (noSig=1; noSig<32; noSig++){
                char * err;
                if ((retour = sigaction (noSig,&act,NULL)) < 0) {
                        err = strerror(errno);
                        sprintf (errmessage, "sigaction %d : ", noSig);
                        strncat (errmessage, err,255);
                        fprintf (stderr, errmessage );
                        fprintf (stderr,"\n");
                }
        }

        while (1) pause();
}

void trapall(int sig, siginfo_t * pinfo, void * pucontext){
        printf("reçu le signal=%d\n", pinfo->si_signo);
        sigaction(pinfo->si_signo, &newact, NULL);
}

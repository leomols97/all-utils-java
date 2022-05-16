#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <fcntl.h>
#include <signal.h>

static char ch[2];
static int p[2];
struct sigaction action;

void lirePipe(int s)
{
        read(p[0], ch, 1);
}

int main(void)
{
        pipe(p);
        action.sa_handler = lirePipe;
        if(fork() == 0)
        {
                close(p[0]);
                while(1) {
                        read(0, ch, 2);
                        write(p[1], ch, 1);
                        kill(getppid(), SIGUSR1);
                }
                exit(0);
        }
        close(p[1]);
        sigaction(SIGUSR1, &action, NULL);
        while(1) write(1,ch,1);
}

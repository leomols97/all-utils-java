#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>

int main(void)
{
        char ligne[257];
        char *tokens[100];
        char errmsg[200];
        printf("$ Commande ?");
        fgets(ligne,256,stdin);
        while(strcmp(ligne,"exit\n"))
        {
                if(fork() == 0)
                {
                        chdir(getenv("HOME"));
                        if(fork() == 0)
                        {
                                chdir("..");
                                execlp("ls", "ls", NULL);
                                exit(1);
                        }
                        wait(0);
                        execlp("ls", "ls", NULL);
                        exit(1);
                }
                wait(0);
                printf("$ Command ?\n");
                fgets(ligne,256,stdin);
        }
}

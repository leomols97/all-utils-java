#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <fcntl.h>
#include <signal.h>
#include <errno.h>
#include <string.h>

void main(void)
{
        int p[2];
        int fd;
        char ligne[257];
        char* tokens[100];
        char errmsg[200];
        printf("$ Commande ?");
        fgets(ligne,256,stdin);
        while(strcmp(ligne, "exit\n"))
        {
                fd = open("err", O_WRONLY | O_CREAT | O_TRUNC, 0644);
                pipe(p);
                if(fork() == 0)
                {
                        dup2(p[1], 1);
                        dup2(fd, 2);
                        close(p[1]);
                        close(p[0]);
                        execlp("ls", "ls", "f", NULL); //remplacer f par un dossier qui existe ou par un dossier qui n'existe pour voir les deux resultat possibles.
                        exit(1);
                }

                if(fork() == 0)
                {
                        dup2(p[0], 0);
                        close(p[0]);
                        close(p[1]);
                        if(fork() == 0)
                        {
                                execlp("cat", "cat", NULL);
                        }
                        wait(0);
                        execlp("cat", "cat", "err", NULL);
                }
                close(fd);
                close(p[0]);
                close(p[1]);
                while(wait(0) > 0);
                printf("$ Commande ? ");
                fgets(ligne,256,stdin);
        }
}

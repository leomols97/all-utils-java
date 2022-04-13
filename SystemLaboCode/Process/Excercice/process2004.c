#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <fcntl.h>

int main()
{
        int fp[2];
        char ligne[257];
        char *tokens[100];
        char errmsg[200];
        printf("$ Commande ? ");
        fgets(ligne,256,stdin);
        while (strcmp(ligne,"exit\n"))
        {
                pipe(fp);
                if(fork() == 0)
                {
                        dup2(fp[1],1);
                        close(fp[0]); close(fp[1]);
                        execlp("ls", "ls", "-ail", NULL);
                }

                if(fork() == 0)
                {
                        dup2(fp[0], 0);
                        close(fp[0]); close(fp[1]);
                        int fd = open("x", O_WRONLY | O_CREAT | O_TRUNC, 0644);
                        dup2(fd, 1);
                        close(fd);
                        execlp("cat", "cat", NULL);
                }
                close(fp[0]); close(fp[1]);
                while(wait(0) > 0);
                printf("$ Commande ? ");
                fgets(ligne,256,stdin);
        }
        exit(0);
}

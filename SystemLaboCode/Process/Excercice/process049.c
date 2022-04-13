#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>

int main ()
{       int fd;
        char ligne[257];
        char *tokens[100];
        char errmsg[200];
        printf("$ Commande ? ");
        fgets(ligne,256,stdin);
        while (strcmp(ligne,"exit\n"))
        {
                fd = open("out", O_WRONLY | O_CREAT | O_APPEND, 0644);
                if(fork() == 0)
                {
                        char* home = getenv("HOME");
                        int ok = chdir(home);
                        dup2(fd, 1);
                        close(fd);
                        execlp("ls", "ls", NULL);
                        exit(0);
                }
                close(fd);
                wait(0);
                printf("$ Commande ? ");
                fgets(ligne,256,stdin);
        }
        exit(0);
}

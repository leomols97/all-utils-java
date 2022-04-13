#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <fcntl.h>
int main ()
{       int i,fd, fdsave;
        char * ligne;
        ligne=(char*)malloc(300);
        printf("$ Commande ?");
        fgets(ligne,256,stdin);
        while (strcmp(ligne,"exit\n"))
        {
                fd = open("x", O_WRONLY | O_CREAT | O_TRUNC, 0644);
                if(fork() == 0)
                {
                        dup2(fd, 1);
                        dup2(fd, 2);
                        close(fd);
                        execlp("ls", "ls", "-ail", NULL);
                }
                close(fd);
                wait(0);
                printf("$ Commande ? ");
                fgets(ligne,256,stdin);
        }
        free (ligne);
        exit(0);
}

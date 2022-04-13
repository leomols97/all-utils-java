#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <fcntl.h>

int main()
{
        char ligne[257];
        char *tokens[100];
        char errmsg[200];
        printf("$ Commande ? ");
        fgets(ligne,256,stdin);
        while (strcmp(ligne,"exit\n"))
        {
                int j;
                if(fork() == 0)
                {
                        execlp("/bin/mkdir", "mkdir", "-p", "brol", NULL);
                }
                wait(&j);

                if(j != 0)
                {
                        printf("Erreur : mkdir non trouvable \n");
                        exit(1);
                }

                if(fork() == 0)
                {
                        chdir("brol");
                        int fd = open("f", O_WRONLY | O_CREAT, 0644);
                        dup2(fd, 1);
                        close(fd);
                        exit(0);
                }
                wait(0);
                printf("$ Commande ? ");
                fgets(ligne,256,stdin);
        }
        exit(0);
}

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <unistd.h>
int main ()
{       int i;
        char ligne[257];
        char *tokens[100];
        char errmsg[200];
        printf("$ ");
        fgets(ligne,256,stdin);
        while (strcmp(ligne,"q\n"))
        {       i=0;
                tokens[i]=strtok(ligne," \n");
                while (tokens[i] != NULL) tokens[++i]=strtok(NULL," \n");
                if(strcmp(tokens[0], "c") == 0)
                {
                        if(fork() == 0)
                                exit(0);
                        if(fork() == 0)
                                exit(0);
                        sleep(1);
                        system("ps");
                }else if(strcmp(tokens[0], "d") == 0)
                {
                        int pid = atoi(tokens[1]);
                        waitpid(pid, 0, 0);
                        system("ps");
                }else
                        printf("Mauvaise commande\n");
                printf("$ ");
                fgets(ligne,256,stdin);
        }
        exit(0);
}

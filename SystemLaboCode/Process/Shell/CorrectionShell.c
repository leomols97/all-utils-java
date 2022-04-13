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
        while (strcmp(ligne,"exit\n"))
        {       i=0;
                tokens[i]=strtok(ligne," \n");
                while (tokens[i] != NULL) tokens[++i]=strtok(NULL," \n");
                if (fork()==0){
                    execvp(tokens[0],tokens);
                    sprintf (errmsg, "exec <%s> :", tokens[0]);
                    perror(errmsg);
                    exit(-1);
                }
                wait(0);
                printf("$ ");
                fgets(ligne,256,stdin);
        }
        exit(0);
}

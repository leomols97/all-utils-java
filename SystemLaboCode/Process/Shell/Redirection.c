#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <fcntl.h>
int main ()
{       int i,h;
        char * ligne;
        char *tokens[100];
        ligne=(char*)malloc(300);
        printf("$ ");
        fgets(ligne,256,stdin);
        while (strcmp(ligne,"exit\n"))
        {       i=0;
                tokens[i]=strtok(ligne," \n");
                while (tokens[i] != NULL) tokens[++i]=strtok(NULL," \n");
                if (fork()==0)
                {       if ((i>1) && (strcmp(tokens[i-2],">")==0))
                        {       h=open(tokens[i-1],O_WRONLY|O_CREAT|O_TRUNC,0644);
                                dup2(h,1);
                                close(h);
                                tokens[i-2]=0;
                        }else if((i > 0) && (strcmp(tokens[i-2],"<")==0))
                        {
                                h=open(tokens[i-1], O_RDONLY);
                                if(h < 0)
                                {
                                        printf("Le fichier %s n'existe pas\n", tokens[i-1]);
                                        break;
                                }
                                dup2(h, 0);
                                close(h);
                                tokens[i-2]=0;
                        }
                        execvp(tokens[0],tokens);
                        perror("exec");
                }
                wait(0);
                printf("$ ");
                fgets(ligne,256,stdin);
        }
        free (ligne);
        exit(0);
}

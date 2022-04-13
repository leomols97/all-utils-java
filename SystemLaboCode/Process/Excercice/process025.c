#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <unistd.h>
#include <dirent.h>
#include <sys/stat.h>

int main ()
{       int i;
        char ligne[257];
        char *tokens[100];
        char errmsg[200];
        printf("$ Commande ? ");
        fgets(ligne,256,stdin);
        while (strcmp(ligne,"exit\n"))
        {       i=0;
                tokens[i]=strtok(ligne," \n");
                struct dirent *dirp;
                DIR *dp;
                dp=opendir(".");
                char * str1 = ".";
                char * str2 = "..";
                char * str3 = "a.out";
                while ((dirp=readdir(dp)) != NULL)
                {
                        if(strcmp(dirp->d_name, str1) != 0 && strcmp(dirp->d_name, str2) != 0 && strcmp(dirp->d_name, str3) != 0)
                        {
                                if(fork() == 0)
                                {
                                        execlp("file", "file", dirp->d_name, NULL);
                                }
                                wait(0);
                        }
                }
                closedir(dp);
                printf("$ Commande ? ");
                fgets(ligne,256,stdin);
        }
        exit(0);
}

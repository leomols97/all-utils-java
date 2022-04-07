#include <stdlib.h>
#include <stdio.h>
#include <sys/stat.h>
#include <dirent.h>
#include <string.h>
int main ()
{
        struct dirent *dirp;
        DIR *dp;
        struct stat sb;
        dp=opendir(".");
        char * str1 = ".";
        char * str2 = "..";
        char** name = NULL;
        int cptName = 0;
        int cptLine = 0;
        while ((dirp=readdir(dp)) != NULL)
        {
                if(stat(dirp->d_name, &sb) == 0 && S_ISDIR(sb.st_mode) && strcmp(dirp->d_name, str1) != 0 && strcmp(dirp->d_name, str2) != 0)
                {
                        cptName++;
                        name = realloc(name, cptName* sizeof(char*));
                        name[cptName - 1] = malloc(256 * sizeof(char));
                        strcpy(name[cptName - 1], dirp->d_name);
                }else
                {
                        printf("%s             ", dirp->d_name);
                        cptLine++;
                        if(cptLine == 3)
                        {
                                printf("\n");
                                cptLine = cptLine % 3;
                        }
                }
        }
        printf("\n \n");

        closedir(dp);

        for(int i = 0; i < cptName; i++)
        {
                cptLine = 0;
                printf("%s : \n", name[i]);
                dp=opendir(name[i]);
                while((dirp=readdir(dp)) != NULL)
                {
                        printf("%s             ", dirp->d_name);
                        cptLine++;
                        if(cptLine == 3)
                        {
                                printf("\n");
                                cptLine = cptLine % 3;
                        }

                }
                printf("\n \n");
                closedir(dp);

        }

        for(int i = 0; i < cptName; i++)
        {
                free(name[i]);
        }

        free(name);
        exit(0);
}


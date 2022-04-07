#include <stdlib.h>
#include <stdio.h>
#include <sys/stat.h>
#include <dirent.h>
#include <string.h>

void treeRecursive(const char* nom, char* currentPath, int space)
{
        struct dirent *dirp;
        DIR *dp;
        struct stat sb;
        dp=opendir(currentPath);
        char * str1 = ".";
        char * str2 = "..";
        char** name = NULL;
        int cptName = 0;
        int cptLine = 0;
        char* copyPath = malloc(999*sizeof(char));
        for(int i = 0; i < space; i++)
        {
                printf(" ");
        }
        printf("%s \n", nom);
        while ((dirp=readdir(dp)) != NULL)
        {
                if(dirp->d_type & DT_DIR)
                {
                        if(strcmp(dirp->d_name, str1) != 0 && strcmp(dirp->d_name, str2) != 0)
                        {
                        //      printf("wow %s %s\n", currentPath, nom);        
                                strcpy(copyPath, currentPath);
                                strcat(copyPath, "/");
                                strcat(copyPath, dirp->d_name);
                                treeRecursive(dirp->d_name, copyPath, space +1);
                        }
                }
        //      printf("%d %s\n", S_ISDIR(sb.st_mode), dirp->d_name);
        }
        free(copyPath);
        closedir(dp);
}

int main(int argc, char* argv[])
{
        struct stat mystat;
        char* path = malloc(999*sizeof(char));
        stat(argv[1], &mystat);
        if(!S_ISDIR(mystat.st_mode))
        {
                printf("Not a directory");
                exit(0);
        }
        strcpy(path, argv[1]);
        treeRecursive(argv[1], path, 0);
        free(path);
}

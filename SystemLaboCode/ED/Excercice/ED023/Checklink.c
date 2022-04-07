#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

int main(int argc, char* argv[])
{
        struct stat mystat;
        lstat(argv[1], &mystat);
        if(S_ISLNK(mystat.st_mode))
                printf("C'est un lien software\n");
        else
                printf("C'est un lien hardware\n");
        exit(0);
}

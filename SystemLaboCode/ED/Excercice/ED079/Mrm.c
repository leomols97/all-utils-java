#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>

int main(int argc, char* argv[])
{
        int n;
        struct stat mystat;

        for(int i = 1; < argc; i++)
        {
                n = stat(argv[i], &mystat);
                if(n != 0)
                {
                        printf("Impossible a supprimer %s car le fichier ou le dossier n'exite pas", argv[i]);
                }else if(S_ISDIR(mystat.st_mode))
                {
                        printf("Impossible a supprimer %s car c'est un dossier", argv[i]);
                }else
                {
                        unlink(argv[i]);
                }
        }
        exit(0);
}

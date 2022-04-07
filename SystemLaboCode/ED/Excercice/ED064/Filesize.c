#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>

int main(int argc, char* argv[])
{
        struct stat mystat;
        int* size = malloc(2*sizeof(int));
        stat(argv[1], &mystat);

        int s = (int)mystat.st_size;
        int b = (int)mystat.st_blocks;

        size[0] = s;
        size[1] = b;

        printf("Taille en bytes : %d \nTaille en blocs : %d \n", size[0], size[1]);
        free(size);
        exit(0);
}

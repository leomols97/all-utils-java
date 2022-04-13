#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main()
{
        int *loc = malloc(sizeof(int));
        loc[0] = 84;
        printf("< %d %p\n", loc[0], loc);
        if(fork() == 0)
        {
                loc[0] = 32;
                printf("fils : %d %p\n", loc[0], loc);
                exit(0);
        }
        wait(0);
        printf("> %d %p\n", loc[0], loc);
        exit(0);
}

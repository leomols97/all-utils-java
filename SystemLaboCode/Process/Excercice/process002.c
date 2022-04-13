#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int glob = 45;

int main()
{
        int loc = 78;
        printf("< %d %d\n", glob, loc);
        if(fork() == 0)
        {
                glob = 65;
                loc = 32;
                exit(0);
        }
        wait(0);
        printf("> %d %d\n", glob, loc);
        exit(0);
}

#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main()
{
        int r;
        if((r=fork()) == 0)
        {
                printf("Je bedge\n");
                sleep(1);
                printf("Je wokge\n");
        }

        printf("Adieu");
        exit(0);
}

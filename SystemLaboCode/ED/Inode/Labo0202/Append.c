#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
int main ()
{       char bufA[]="Hey hey";
        char bufB[]="Peace and die";
        int handle;
        handle=open("FichCreux.dat",O_WRONLY|O_CREAT|O_APPEND,0644);
        if (handle < 0){
                perror ("FichierCreux.dat");
                exit(1);
        }

        write(handle,bufA,strlen(bufA));
        lseek(handle,100000,SEEK_SET);
        write(handle,bufB,strlen(bufB));
        close(handle);
        exit(0);
}

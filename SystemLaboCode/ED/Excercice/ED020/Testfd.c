#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

void writerfct(int fd, char character)
{
        char c = character;
        for(int i = 0; i < 10; i++)
        {
                write(fd, &c, 1);
        }
}

void readerfct(int fd)
{
        int n;
        char c;
        lseek(fd, 0, SEEK_SET);
        while((n=read(fd, &c, 1)) > 0)
                write(1, &c, 1);
        printf("\n");
}

int main(int argc, char* argv[])
{
        int fd1, fd2, fd3;
        fd1 = open(argv[1], O_RDWR);
        fd2 = dup(fd1);
        fd3 = open(argv[1], O_RDWR);
        printf("fd1 : %d \nfd2 : %d\nfd3 : %d\n", fd1, fd2, fd3);
        writerfct(fd1, 'a');
        readerfct(fd1);
        writerfct(fd2, 'b');
        readerfct(fd2);
        writerfct(fd3, 'c');
        readerfct(fd3);
        close(fd1);
        close(fd2);
        close(fd3);
        exit(0);
}

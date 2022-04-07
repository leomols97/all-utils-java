#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>

int main(int argc, char* argv[])
{
        int fd1, fd2;
        fd1 = open(argv[1], O_RDONLY | O_CREAT, 0644);
        fd2 = open(argv[2], O_WRONLY | O_CREAT | O_TRUNC, 0644);
        char c;
        int n;
        while((n=read(fd1, &c, 1) > 0))
        {
                write(fd2, &c, 1);
        }

        close(fd1);
        close(fd2);
        exit(0);
}

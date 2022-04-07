#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

int main(int argc, char* argv[])
{
        int fd;
        char c;
        fd = open("abctest", O_WRONLY | O_CREAT, 0644);

        c = 'a';
        write(fd, &c, 1);

        lseek(fd, 1000, SEEK_SET);
        c = 'b';
        write(fd, &c, 1);

        lseek(fd, 10000, SEEK_SET);
        c = 'c';
        write(fd, &c, 1);

        close(fd);
        exit(0);
}

#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

/**
 * Note il suffit de changer les droits du fichier confidentiel et mettre dans le open
 * en dessous, un mode d'ouverture qui n'est pas autorisé par le fichier.
 * Exemple : le fichier confidentiel a comme droit rw-r--r--
 *           et qu'on met ici comme mode d'ouverture O_WRONLY ou O_RDWR
 *           bah ca va déclencher l'erreur voulu.
 * Voila voila, c'était le tuto de Chen (alias PH).
 */

int main(int argc, char* argv[])
{
        int fd;
        printf("hey fichier toujours pas ouvert\n");
        printf("je peux toujours print t'as vu\n");
        printf("continue de print meme si je sais que je ne peux pas ouvrir le fichier\n");
        printf("go open le fichier en écriture alors que je n'ai pas les droits en écriture\n");
        fd = open(argv[1], O_RDONLY | O_WRONLY);
        if(fd == -1)
        {
                printf("wow le fichier ne s'est pas ouvert comme c'est incroyable.\n");
                printf("preuve ici : %d\n", fd);
                exit(0);
        }
        close(fd);
        printf("heu what");
        exit(0);
}

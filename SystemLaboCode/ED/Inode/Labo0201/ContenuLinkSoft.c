#include <stdlib.h>
#include <stdio.h>
#include <sys/stat.h>
#include <unistd.h>
#include <sys/types.h>

int main ( int argc, char * argv[] )
{       int r;
        struct stat inode;
        if (argc!=2)
        {       printf("Vous devez donner un nom de fichier \n");
                exit(1);
        }
        r=lstat(argv[1],&inode);
        if (r<0)
        {       perror(argv[1]);
                exit(1);
        }
        if (S_ISLNK(inode.st_mode))
        {       printf("Le fichier %s est un lien software\n",argv[1]);
                printf("Numéro d'inode : %d\n",(int)inode.st_ino);
                printf("Nombre de liens hardware : %d\n",(int)inode.st_nlink);
                printf("Propriétaire du fichier : %d\n",(int)inode.st_uid);
                printf("Groupe du propriétaire du fichier : %d\n",(int)inode.st_gid);
                printf("Taille (en bytes) : %d\n",(int)inode.st_size);
                printf("Nombre de blocs (en secteurs) : %d\n",(int)inode.);
        }
        else
        {       printf("Le fichier %s n'est pas un lien software\n",argv[1]);
                printf("Réessaye l'année prochaine.");
                exit(1);
        }
        exit(0);
}
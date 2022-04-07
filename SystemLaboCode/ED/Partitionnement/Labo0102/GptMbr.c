#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>

typedef struct ETP { // Entrée Table des Partitions
        unsigned char fill [8];
        int first ; // LBA
        int sectors ; // LBA
        } __attribute__ (( packed )) ETP ; // éviter l’alignement des données

typedef struct MBR {
        unsigned char code [442];
        unsigned char signature [4];
        ETP TablePart [4];
        unsigned char bootable [2];
} __attribute__ (( packed )) MBR ;

int main ( int argc , char * argv [] )
        { int fd , first , sectors ;
        MBR struMBR ;
        if (argc != 2 )
        { printf ("usage : LectPart <pilote >\n " );
        exit (1);
        }

        fd = open ( argv [1] , O_RDONLY );

        if(fd < 0)
        {
                perror ( argv [1]);
                exit (1);
        }

        printf ("Partitions primaires de %s :\n" , argv [1]);
        printf ("===================================\n" );
        read (fd ,& struMBR ,512); // Lecture du premier secteur du disque

        printf("%x \n", struMBR.TablePart[0].fill[4]);

        close ( fd );
        exit (0);
}
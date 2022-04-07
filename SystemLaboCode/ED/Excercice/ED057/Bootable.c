#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>

typedef struct ETP {    // Entrée Table des Partitions
        unsigned char fill[8];
        int first;      // LBA
        int sectors;    // LBA
} __attribute__ ((packed)) ETP;   //éviter l'alignement des données.

typedef struct MBR {
    unsigned char code[442];
    unsigned char signature[4];
    ETP TablePart[4];
    unsigned char bootable[2];
} __attribute__ ((packed)) MBR;

int main ( int argc, char * argv[] )
{       int fd,first, sectors;
        MBR struMBR;
        char type;
        if ( argc != 2 )
                { printf("usage : LectPart <pilote>\n");
                  exit(1);
                }
        fd=open(argv[1],O_RDONLY);
        if ( fd < 0)
                {
                  perror(argv[1]);
                  exit(1);
                }
        printf("Partitions primaires de %s :\n",argv[1]);
        printf("===================================\n");
        read(fd,&struMBR,512);  // Lecture du premier secteur du disque
        for (int primaire=1;primaire<=4;primaire++)
        {
                first=struMBR.TablePart[primaire-1].first;
                sectors=struMBR.TablePart[primaire-1].sectors;
                if(first > 0)
                        printf("Partition %d, debut %d - fin %d (%d secteurs) \n", primaire, first, first+sectors-1, sectors);
                printf("Bootable : ");
                char c;
                c = struMBR.TablePart[primaire-1].fill[0];
                if(c == 0x80)
                        printf("oui \n");
                else
                        printf("non \n");
        }
        close(fd);
        exit(0);
}

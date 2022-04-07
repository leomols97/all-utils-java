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
        MBR struEBR;
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
                        printf("%d \n", primaire);
                        first=struMBR.TablePart[primaire-1].first;
                        sectors=struMBR.TablePart[primaire-1].sectors;
                        type=struMBR.TablePart[primaire-1].fill[4];
                        if (first > 0) printf ("Partition %d , type %X , début %d - fin %d (%d secteurs) \n", primaire, type, first, first+sectors-1, sectors);
                        if(type == 0x05 || type == 0x0f) //Pour check si c'est une partition étendue
                        {
                                int firstEntry, sectors;
                                int next = first;
                                char type;
                                printf("Partition etendu \n");
                                do
                                {
                                        lseek(fd, next, SEEK_SET); //Pour aller lire le EBR
                                        read(fd, &struEBR, 512);
                                        firstEntry=struEBR.TablePart[0].first; //Toute les infos de la part logique se trouve dans la première entrée de la table des descripteurs
                                        sectors=struEBR.TablePart[0].sectors;
                                        type=struEBR.TablePart[0].fill[4];
                                        next=struEBR.TablePart[1].first; // Addresse du suivant ou 0 si c'est le dernier
                                        printf("type %X , début %d - fin %d (%d secteurs), suivant %d \n", type, firstEntry, firstEntry+sectors-1, sectors, next);
                                }while(next != 0);

                        }
                }
        close(fd);
        exit(0);
}

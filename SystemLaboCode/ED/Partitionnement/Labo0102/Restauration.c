#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <inttypes.h>  // pout PRIu64
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>

typedef struct{
        char data [512];
}__attribute__((packed)) mbr_t;

typedef struct{
        char filler1 [32];
        uint64_t autreGPT_lba; // adresse de l'autre GPT
        char filler2 [472];

}__attribute__((packed)) headerGpt_t;

typedef struct{
        unsigned char guid[16];
}__attribute__((packed)) guid_t;

typedef struct{
        guid_t type; // type de la partition
        guid_t id; // son id unique dans la table GPT
        uint64_t first_lba; // adresse du 1er secteur
        uint64_t last_lba; // adresse du dernier secteur
        char revision[8]; //espace reservé
        char name[72]; // nom de la partition
}__attribute__((packed)) entryGpt_t;

int main ( int argc , char * argv [])
{
        int fd, fd2;
        char c;
        headerGpt_t headerGPT;
        uint64_t position;

        if ( argc <2){
                printf(" usage %s < chemin device > " , argv [0]);
                exit (2);
        }

        fd = open ( argv [1] , O_RDONLY ); //Pour écrire au début
        fd2 = open ( argv [1] , O_RDONLY ); //Pour choper l'entrée a la fin

        if ( fd <0){
                printf("device impossible à lire ou inexistant \n" );
                exit (1); // pas le bon peripherique ou pas les droits
        }

        //Choper l'adresse de la parition 2 a la fin
        position = lseek(fd2 , -(33*512) , SEEK_END ); // revenir aux descripteurs
        position = lseek(fd2, 128, SEEK_CUR); // avancer au deuxième descripteur

        entryGpt_t entry ;
        read (fd2 , &entry , sizeof(entryGpt_t)); // choper les données de l'entrée

        lseek(fd, 1152, SEEK_SET); //Avancé jusqu'au descripteur 2
        guid_t type = entry.type;
        write(fd, &type, 16);

        guid_t id = entry.id;
        write(fd, &id, 16);

        uint64_t data = entry.first_lba;
        write(fd, &data, 8);

        data = entry.last_lba;
        write(fd, &data, 8);

        for(int i = 0; i < 7; i++)
        {
                char c = entry.revision[i];
                write(fd, &c, 1);
        }

        for(int i = 0; i < 71; i++)
        {
                char c = entry.name[i];
                write(fd, &c, 1);
        }
        close(fd);
        close(fd2);
        exit(0);
}

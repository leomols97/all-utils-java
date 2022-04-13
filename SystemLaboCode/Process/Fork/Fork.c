#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
// vérifions la valeur et l'adresse d'une variable dans père et fils
int main ( )
{       int var,r; // var est la variable en examen
        var=6;
        static int monStatic = 56;
        int* ptr = malloc(sizeof(int));
        ptr[0] = 99;
        printf("Avant le fork, pour le père %d, l'adresse %p contient %d\n", getpid(),(void*)&var,var);
        printf("Valeur de static %d adresse %p  pour le père %d\n", monStatic, (void*) &monStatic,  getpid());
        printf("Valeur de malloc %d adresse %p pour le père %d\n", *ptr, (void*) ptr, getpid());
        if ((r=fork())==0){
                printf ("Voici %d, fils de %d\n", getpid(), getppid());
                printf("pour %d fils de %d, l'adresse %p contient %d\n", getpid(),getppid(), (void*)&var,var);
                printf("Valeur de static %d adresse %p pour le fils %d\n", monStatic, (void*) &monStatic,  getpid());
                printf("Valeur de malloc %d adresse %p pour le père %d\n", *ptr, (void*) ptr, getpid());
                var=19;
                monStatic = 42;
                ptr[0] = 12;
                //sleep(3); // décommenter
                printf("pour %d, fils de %d, l'adresse %p contient %d\n", getpid(),getppid(), (void*)&var,var);
                printf("Valeur de static %d adresse %p pour le fils %d\n", monStatic, (void*) &monStatic,  getpid());
                printf("Valeur de malloc %d adresse %p pour le père %d\n", *ptr, (void*) ptr, getpid());
                free(ptr);
                exit(0); // commenter
        }

        sleep(20);
        printf("Après le fork, pour le père %d, l'adresse %p contient %d\n", getpid(),(void*)&var,var);
        printf("Après fork valeur de static %d adresse %p  pour le père %d\n", monStatic, (void*) &monStatic,  getpid());
        printf("Valeur de malloc %d adresse %p pour le père %d\n", *ptr, (void*) ptr, getpid());
        var=13;
        monStatic = 182;
        ptr[0] = 30;
        sleep(5); // décommenter
        printf("Après le fork, pour le père %d, l'adresse %p contient %d\n", getpid(),(void*)&var,var);
        printf("Après fork valeur de static %d adresse %p  pour le père %d\n", monStatic, (void*) &monStatic,  getpid());
        printf("Valeur de malloc %d adresse %p pour le père %d\n", *ptr, (void*) ptr, getpid());
        //wait(0); // commenter
        free(ptr);
        exit(0);
}

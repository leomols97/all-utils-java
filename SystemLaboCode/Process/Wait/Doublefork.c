#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<sys/wait.h>
int main(int argc, char * argv[])
{       char spid[100];
        int r;
        int r2;
        if ((r=fork()) == 0)
        {
                if((r2 = fork()) == 0)
                {
                        sleep(10);
                        exit(0);
                }
                exit(0);
        }
        printf("Le process fils qui devient zombie est le numéro %d\n",r);
        printf("Résultat affiché par ps : table des process\n\n");
        sleep(1);
        sprintf(spid,"ps -o ppid,pid,state,command"); // juste pour montrer un appel à sprintf ;-)
        system(spid);
        printf("\nJ'appelle wait, une bonne façon d'éliminer un zombie\n\n");
        sleep(1);
        wait(0);
        sleep(1);
        printf("Résultat affiché par ps après le wait : table des process\n\n");
        system(spid);
        sleep(10);
        system(spid);
        exit(0);
}

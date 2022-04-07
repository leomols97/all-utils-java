#ifndef GIRL_H
#define GIRL_H

#include <Personne.h>

class girl : public Personne
{
    int age;
    //void tellAge();

public :

    void tellAge();
    girl(int age);
    void sayAge();

};

#endif // GIRL_H

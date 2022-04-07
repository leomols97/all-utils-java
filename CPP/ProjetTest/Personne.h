#ifndef PERSONNE_H
#define PERSONNE_H

#include <stdlib.h>
#include <iostream>
#include <string>

class Personne
{
    virtual void tellAge() = 0;

public :

    virtual ~Personne() {};

};

#endif // PERSONNE_H

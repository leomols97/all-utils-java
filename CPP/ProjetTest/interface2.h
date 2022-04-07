#ifndef INTERFACE2_H
#define INTERFACE2_H

#include "interface1.h"

class interface2 : public interface1
{
public:
    virtual void fct2() = 0;
};

#endif // INTERFACE2_H

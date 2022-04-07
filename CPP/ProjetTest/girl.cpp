#include "girl.h"

girl::girl(int age) : age{age}
{}

void girl::tellAge()
{
    std::cout << this->age <<std::endl;
}

void girl::sayAge()
{
    std::cout << "Voici mon age" << std::endl;
    tellAge();
}

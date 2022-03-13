#include <iostream>
#include <vector>
#include <map>
using namespace std;

enum TestValue {LOL = 1, MDR = 2};
std::map<int, int> m {
    {1, 'm'}
};

int main()
{
    std::string s {"global"};
    if(s.at(1) == 'l')
    {
        cout << "wow" << endl;
    }
    return 0;
}

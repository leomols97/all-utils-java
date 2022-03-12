#include <iostream>
#include <vector>
using namespace std;

enum TestValue {LOL = 'L'};

int main()
{
    std::vector<int> a {};
    a.push_back(1);
    a.push_back(2);
    a.push_back(3);
    a.push_back(4);
    a.push_back(5);
    for(auto i : a)
    {
        cout << i << endl;
    }
    return 0;
}

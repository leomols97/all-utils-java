#include <iostream>
#include <vector>
#include <map>
#include <fstream>
#include "girl.h"
#include "implementer.h"
//#include <thread>

using namespace std;

enum TestValue {LOL, MDR};
std::map<int, int> m {
    {1, 'm'}
};

void testException(int i)
{
    if(i < 0)
    {
        throw std::invalid_argument("heyooo wtf");
    }

    cout << "ok c'est bon" << endl;
}

void test()
{
    testException(-1);
}

void pointeurTest(int* ptr) {
    cout << ptr << endl;
}

int main()
{
//    cout << TestValue::LOL - 1 << endl;

//    ofstream Myfile("filename.txt"); //--> juste mettre le chemin.

//    Myfile << "my text what ?";

//    Myfile.close();

//    cout << "file created poooog" << endl;

//    std::ifstream myfile;
//    myfile.open("");
//    std::string s;

//    if(myfile.is_open()) {
//        while(myfile >> s) {
//            cout << s << endl;
//        }
//        myfile.close();
//    }else
//    {
//        cout << "wtf man" << endl;
//    }

//    pos.getX() > (int)(this->_board.size()/2.0 + 0.5) && pos.getX() < (int)this->_board.size() - 1
//                    && pos.getY() > 0 && pos.getY() < (int)this->_board.size() - 1;

//    while(true)
//    {
//        try {
//            cout << "damn" << endl;
//            test();
//        }  catch (std::invalid_argument & exception) {
//            cout << exception.what() << endl;
//        }
//    }

//    std::string s = "1230";
//    int secondValue = std::stoi(s.substr(1));
//    int i = s.at(0);
//    std::cout << secondValue << std::endl;
//    int* ptr {new int};
//    ptr[0] = 1;
//    cout << ptr[0] << endl;
//    delete(ptr);

    //int i = TestValue::LOL;
    int test = (1+1);
    int test = (1+1)%2;
    cout << test << endl;

    return 0;
}

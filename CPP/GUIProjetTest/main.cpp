#include "todolist.h"

#include <QApplication>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);

    CToDoList app;
    app.show();
    CToDoList app2;
    app2.show();
    return a.exec();
}

#include "first.h"
#include "ui_mainwindow.h"
#include <QPixmap>

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
    //, ui2(new Ui2::MainWindow2)
{
    ui->setupUi(this);
    QPixmap pix(":/resources/applepie.png");
    ui->label->setPixmap(pix.scaled(100,100, Qt::KeepAspectRatio));
    ui->label->setScaledContents(true);
    ui->label_10->setPixmap(pix.scaled(100,100, Qt::KeepAspectRatio));
    ui->label_4->setPixmap(pix.scaled(100,100, Qt::KeepAspectRatio));
}

MainWindow::~MainWindow()
{
    delete ui;
}


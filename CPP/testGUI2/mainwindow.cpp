#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QTableView>
#include <QLabel>
#include <QGraphicsScene>
#include <QGraphicsView>
#include <QGraphicsGridLayout>

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    QGraphicsScene* scene = new QGraphicsScene;
    ui->graphicsView->setScene(scene);

    // Add the vertical lines first, paint them red
    for (int x=0; x<=50; x+=10)
        scene->addLine(x,0,x,50, QPen(Qt::red));

    // Now add the horizontal lines, paint them green
    for (int y=0; y<=50; y+=10)
        scene->addLine(0,y,50,y, QPen(Qt::green));

    // Fit the view in the scene's bounding rect
    //ui->graphicsView->fitInView(scene->itemsBoundingRect());
}

MainWindow::~MainWindow()
{
    delete ui;
}


#include "first.h"
#include "container.h"
#include <QGraphicsScene>
#include <QGraphicsView>

#include <QApplication>

int main(int argc, char *argv[])
{
    QApplication app(argc, argv);

//! [0]
//! [1]
    QGraphicsScene scene(-200, -200, 400, 400);

    QGraphicsView view(&scene);
    view.setRenderHint(QPainter::Antialiasing);
    view.setViewportUpdateMode(QGraphicsView::BoundingRectViewportUpdate);
    view.setBackgroundBrush(QColor(230, 200, 167));
    view.setWindowTitle("Drag and Drop Robot");
    view.show();

    return app.exec();
}

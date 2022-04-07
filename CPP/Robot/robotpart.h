#ifndef ROBOTPART_H
#define ROBOTPART_H

#include <QMainWindow>
#include <QGraphicsObject>
#include <QGraphicsSceneDragDropEvent>
#include <QColor>
#include <QMimeData>

class RobotPart : public QGraphicsObject
{
public:
    RobotPart(QGraphicsItem *parent = nullptr);

protected:
    void dragEnterEvent(QGraphicsSceneDragDropEvent *event) override;
    void dragLeaveEvent(QGraphicsSceneDragDropEvent *event) override;
    void dropEvent(QGraphicsSceneDragDropEvent *event) override;

    QColor color = Qt::lightGray;
    bool dragOver = false;
};


#endif // ROBOTPART_H

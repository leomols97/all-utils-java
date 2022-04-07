#ifndef CONTAINER_H
#define CONTAINER_H

#include <QGraphicsItem>
#include <QIcon>

class container : public QGraphicsObject
{
public:
    container(QGraphicsItem *parent = nullptr);

protected:
    void dragEnterEvent(QGraphicsSceneDragDropEvent *event) override;
    void dragLeaveEvent(QGraphicsSceneDragDropEvent *event) override;
    void dropEvent(QGraphicsSceneDragDropEvent *event) override;

    QIcon image = QIcon(":/resources/applepie.png");
    bool dragOver = false;
};

#endif // CONTAINER_H

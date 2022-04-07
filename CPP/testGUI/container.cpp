#include "container.h"

#include <QGraphicsSceneDragDropEvent>

container::container(QGraphicsItem *parent)
    : QGraphicsObject(parent)
{
    setAcceptDrops(true);
}
//! [0]

//! [1]
void container::dragEnterEvent(QGraphicsSceneDragDropEvent *event)
{
    event->setAccepted(true);
    dragOver = true;
    update();
}
//! [1]

//! [2]
void container::dragLeaveEvent(QGraphicsSceneDragDropEvent *event)
{
    Q_UNUSED(event);
    dragOver = false;
    update();
}
//! [2]

//! [3]
void container::dropEvent(QGraphicsSceneDragDropEvent *event)
{
    dragOver = false;
    update();
}

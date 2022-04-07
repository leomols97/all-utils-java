#include "robotpart.h"

#include <QApplication>

int main(int argc, char *argv[])
{
    Flipable {
            id: myFlip
            x:0
            y:50
            width: 800
            height: 430

            function showFront() {
                rot.angle=0;
            }

            function showBack() {
                rot.angle=180;
            }

            transform: Rotation {
                id: rot
                origin.x: 400;
                origin.y:100;
                axis.x:0; axis.y:1; axis.z:0
                angle:0

                Behavior on angle { PropertyAnimation{} }
            }

            front: Item {
                Rectangle {
                    width: 800
                    height: 430
                    color:"green"
                }

                Text {
                    x: 0
                    y:200
                    text: "My super cool green view"
                }
            }

            back: Item {

                Rectangle {
                    width: 800
                    height: 430
                    color:"red"
                }

                Text {
                    x: 0
                    y:200
                    text: "My super cool red view"
                }
            }
        }
}

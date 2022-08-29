package atl.architetural.mvc;

import atl.observer.Observable;
import atl.observer.Observer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 *
 * @author Leong Paeg-Hing
 */
public class ViewBinary implements Observer{
    private Text text;

    public ViewBinary(Stage primaryStage) {
        System.out.println("DEBUG | VIEW2      | Construction");

        text = new Text();

        HBox box = new HBox(20, text);
        box.setAlignment(Pos.CENTER);
        Scene scene = new Scene(box);
        Stage secondStage = new Stage();
        secondStage.setMinHeight(100);
        secondStage.setMinWidth(100);
        double centerXPosition = primaryStage.getX() + primaryStage.getWidth();
        double centerYPosition = primaryStage.getY() + primaryStage.getHeight() / 2d;
        secondStage.setX(centerXPosition);
        secondStage.setY(centerYPosition);
        secondStage.setScene(scene);
        secondStage.show();
    }

    @Override
    public void update(Observable observable, Object arg) {
        Model model = (Model) observable;
        int data = model.getData();
        System.out.println("DEBUG | VIEW2      | Mise à jour de l'observateur : l'entier vaut " + data);
        setNumber(data);
    }

    public void initialize(int data) {
        System.out.println("DEBUG | VIEW2      | Initialisation : l'entier vaut " + data);
        setNumber(data);
    }

    public void setNumber(int data) {
        text.setText("" + Integer.toBinaryString(data));
    }
}

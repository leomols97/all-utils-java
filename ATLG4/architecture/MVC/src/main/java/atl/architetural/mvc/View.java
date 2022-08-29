package atl.architetural.mvc;

import atl.handler.ButtonHandler;
import atl.observer.Observable;
import atl.observer.Observer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;

/**
 *
 * @author jlc
 */
public class View implements Observer {

    private Button button;
    private Circle circle;
    private Text text;

    public View(Stage stage) {
        System.out.println("DEBUG | VIEW       | Construction : le cercle est blanc");
        button = new Button("Bouton");
        circle = new Circle(25);
        circle.setStyle("-fx-fill:white");

        text = new Text();
        text.setBoundsType(TextBoundsType.VISUAL);
        StackPane stack = new StackPane();
        stack.getChildren().addAll(circle, text);

        HBox box = new HBox(20, button, stack);
        box.setAlignment(Pos.CENTER);
        Scene scene = new Scene(box);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void update(Observable observable, Object arg) {
        System.out.println("DEBUG | VIEW       | Mise à jour de l'observateur : le cercle est jaune");
        setColorYellow();
        Model model = (Model) observable;
        int data = model.getData();
        System.out.println("DEBUG | VIEW       | Mise à jour de l'observateur : l'entier vaut " + data);
        setNumber(data);
    }

    public void initialize(int data) {
        System.out.println("DEBUG | VIEW       | Initialisation : le cercle est vert");
        System.out.println("DEBUG | VIEW       | Initialisation : l'entier vaut " + data);
        circle.setStyle("-fx-fill:green");
        text.setText("" + data);
    }

    public void disableBouton() {
        System.out.println("DEBUG | VIEW       | Changement de l'interface : le bouton est désactivé");
        button.setDisable(true);
    }

    public void addHandlerButton(Controller controller) {
        System.out.println("DEBUG | VIEW       | Ajoute un lien entre le bouton et une action : le bouton appelle le controlleur");
        ButtonHandler handler = new ButtonHandler(controller);
        button.setOnAction(handler);
    }

    private void setColorYellow() {
        circle.setStyle("-fx-fill:yellow");
    }

    private void setNumber(int data) {
        text.setText("" + data);
    }
}

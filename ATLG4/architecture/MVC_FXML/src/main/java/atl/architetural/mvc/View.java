package atl.architetural.mvc;

import atl.observer.Observable;
import atl.observer.Observer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author jlc
 */
public class View implements Observer {

    private FxmlController mainPane;

    public View(Stage stage) throws IOException {
        System.out.println("DEBUG | VIEW       | Construction : le cercle est blanc");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/simpleView.fxml"));
        Parent root = loader.load();
        mainPane = loader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void update(Observable observable, Object arg) {
        System.out.println("DEBUG | VIEW       | Mise à jour de l'observateur : le cercle est jaune");
        Model model = (Model) observable;
        int data = model.getData();
        System.out.println("DEBUG | VIEW       | Mise à jour de l'observateur : l'entier vaut " + data);
        mainPane.update(data);
    }

    public void initialize(int data) {
        System.out.println("DEBUG | VIEW       | Initialisation : le cercle est vert");
        System.out.println("DEBUG | VIEW       | Initialisation : l'entier vaut " + data);
        mainPane.initialize(data);
    }

    public void disableBouton() {
        System.out.println("DEBUG | VIEW       | Changement de l'interface : le bouton est désactivé");
        mainPane.disableBouton();
    }

    public void addHandlerButton(Controller controller) {
        System.out.println("DEBUG | VIEW       | Ajoute un lien entre le bouton et une action : le bouton appelle le controlleur");
        mainPane.addHandlerButton(controller);
    }

    public void setColorYellow() {
        mainPane.setColorYellow();
    }

    public void setNumber(int data) {
        mainPane.setNumber(data);
    }
}

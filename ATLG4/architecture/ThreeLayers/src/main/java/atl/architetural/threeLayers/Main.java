package atl.architetural.threeLayers;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author jlc
 */
public class Main extends Application {
    /**
     * Entry points to the <code> Lotto </code> application..
     *
     * @param args no arguments needed.
     */
    public static void main(String[] args) {
        launch(args);
    }

    public Main() {
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("DEBUG | MAIN       | Début du programme");
        
        Model model = new Model();
        View view = new View(stage,model);
        
        view.addHandlerButton();
        System.out.println("");
        
        view.initialize();
        System.out.println("");
    }

}

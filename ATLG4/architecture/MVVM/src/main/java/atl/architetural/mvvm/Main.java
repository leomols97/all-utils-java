package atl.architetural.mvvm;

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
        View view = new View(stage);
        ViewBinary viewBinary = new ViewBinary(stage);
        ViewModel viewModel = new ViewModel(model);
        System.out.println("");

        System.out.println("DEBUG | MAIN       | Ajoute le lien observateur-observé entre le viewmodel et le modèle");
        model.addObserver(viewModel);
        System.out.println("");

        viewModel.initialize();
        System.out.println("");
        view.bind(viewModel);
        viewBinary.bind(viewModel);
    }

}

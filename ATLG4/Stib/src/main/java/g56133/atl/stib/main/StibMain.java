package g56133.atl.stib.main;

import g56133.atl.stib.model.core.Facade;
import g56133.atl.stib.model.core.Model;
import g56133.atl.stib.presenter.Presenter;
import g56133.atl.stib.view.View;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Leong Paeg-Hing
 */
public class StibMain extends Application{
    
    public static void main(String[] args) throws IOException {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = 
                new FXMLLoader(getClass().getResource("/fxml/Stib.fxml"));
        
        Facade facade = new Facade();
        
        View view = new View();
        
        Presenter presenter = new Presenter(facade, view);
        
        facade.registerObserver(presenter);
        
        loader.setController(view); // set a controller
        
        VBox root = loader.load();
        
        presenter.initialiseSearchBox(); // initialise the searchboex data
        
        view.setUpHandler(presenter); // create a the handler to search the shortest path.
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
        stage.show();
    }
}

package g56133.atl.SortingRace.main;

import g56133.atl.SortingRace.model.Facade;
import g56133.atl.SortingRace.model.Model;
import g56133.atl.SortingRace.view.SortingRaceController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Leong Paeg-Hing
 */
public class SortingRaceMain extends Application{
    
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = 
                new FXMLLoader(getClass().getResource("/fxml/sort.fxml"));
        
        Model model = new Facade();
        
        loader.setController(new SortingRaceController(model));
        
        VBox root = loader.load();
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
        stage.show();
    }
    
}

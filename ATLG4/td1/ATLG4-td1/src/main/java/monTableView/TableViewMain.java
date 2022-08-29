/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monTableView;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Leong Paeg-Hing
 */
public class TableViewMain extends Application {

    public static void main(String[] args) {
        System.out.println("aaa");
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
//        FXMLLoader loader
//                = new FXMLLoader(getClass().getResource("/fxml/MonTableView.fxml"));
//        System.out.println("a2");
//        TableViewController ctrl = new TableViewController();
//        System.out.println("a3");
//        loader.setController(ctrl);
//        System.out.println("a4");
//        AnchorPane root = loader.load();
//        System.out.println("a5");
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();

        System.out.println("a2");

        Parent root
               = FXMLLoader.load(getClass().getResource("/fxml/MonTableView.fxml"));

        System.out.println("a2");

        Scene scene = new Scene(root);

        System.out.println("a3");

        stage.setScene(scene);
        stage.show();
    }

}

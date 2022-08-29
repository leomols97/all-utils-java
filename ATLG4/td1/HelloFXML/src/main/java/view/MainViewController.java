package view;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import static view.MainApp.FORMATTER_TIME;

public class MainViewController /*implements Initializable*/ {

    @FXML
    private Label answer;

    @FXML
    private Label time;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        answer.setText("42");
    }

    public MainViewController() {
        System.out.println("");
        System.out.println(LocalDateTime.now().format(FORMATTER_TIME)
                + " FXCONTROLLER | CONSTRUCTEUR | DEBUT");
        System.out.println("\tanswer " + answer);
        System.out.println(LocalDateTime.now().format(FORMATTER_TIME)
                + " FXCONTROLLER | CONSTRUCTEUR | FIN");
    }

    /**
     * @Override public void initialize(URL url, ResourceBundle rb) {
     * System.out.println(LocalDateTime.now().format(FORMATTER_TIME) + "
     * FXCONTROLLER | INITIALIZE | DEBUT"); System.out.println("\turl " + url);
     * System.out.println("\trb " + rb); System.out.println("\tanswer " +
     * answer);
     *
     * time.setText(LocalDate.now().toString());
     *
     * System.out.println(LocalDateTime.now().format(FORMATTER_TIME) + "
     * FXCONTROLLER | INITIALIZE | FIN"); System.out.println(""); }
     */

    public void initialize() {
        System.out.println(LocalDateTime.now().format(FORMATTER_TIME)
                + " FXCONTROLLER | INITIALIZE | DEBUT");
        time.setText(LocalDate.now().toString());
        System.out.println("hello i'm here");

        System.out.println("");
    }
}

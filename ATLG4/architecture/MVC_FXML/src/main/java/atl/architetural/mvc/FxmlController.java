package atl.architetural.mvc;

import atl.handler.ButtonHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class FxmlController {
    @FXML
    private Button button;
    @FXML
    private Circle circle;
    @FXML
    private Text text;

    public FxmlController() {
    }

    public void initialize(int data) {
        circle.setStyle("-fx-fill:green");
        text.setText("" + data);
    }

    public void disableBouton() {
        button.setDisable(true);
    }

    public void addHandlerButton(Controller controller) {
        ButtonHandler handler = new ButtonHandler(controller);
        button.setOnAction(handler);
    }

    void setColorYellow() {
        circle.setStyle("-fx-fill:yellow");
    }

    public void setNumber(int data) {
        text.setText("" + data);
    }

    public void update(int data) {
        setColorYellow();
        setNumber(data);
    }
}

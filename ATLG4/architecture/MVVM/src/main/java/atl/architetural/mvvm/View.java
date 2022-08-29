package atl.architetural.mvvm;

import atl.handler.ButtonHandler;
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
public class View {

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

    public void bind(ViewModel viewModel) {
        System.out.println("DEBUG | VIEW       | Ajoute le binding entre la vue et le viewmodel");
        text.textProperty().bind(viewModel.getValue());
        circle.styleProperty().bind(viewModel.getStyleProperty());
        button.disableProperty().bind(viewModel.getButtonProperty());
        ButtonHandler handler = new ButtonHandler(viewModel);
        button.onActionProperty().setValue(handler);
    }
}

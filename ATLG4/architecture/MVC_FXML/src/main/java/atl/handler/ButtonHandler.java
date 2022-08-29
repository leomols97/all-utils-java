package atl.handler;

import atl.architetural.mvc.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author jlc
 */
public class ButtonHandler implements EventHandler<ActionEvent> {

    private Controller controller;

    public ButtonHandler(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void handle(ActionEvent t) {
        System.out.println("DEBUG | VIEW       | On appuie sur le bouton");
        controller.doSomething();
    }

}

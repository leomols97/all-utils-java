package atl.handler;

import atl.architetural.mvp.Presenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author jlc
 */
public class ButtonHandler implements EventHandler<ActionEvent> {

    private Presenter presenter;

    public ButtonHandler(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void handle(ActionEvent t) {
        System.out.println("DEBUG | VIEW       | On appuie sur le bouton");
        presenter.doSomething();
    }

}

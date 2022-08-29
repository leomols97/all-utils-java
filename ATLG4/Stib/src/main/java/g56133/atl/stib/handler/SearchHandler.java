package g56133.atl.stib.handler;

import g56133.atl.stib.presenter.Presenter;
import g56133.atl.stib.view.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author Leong Paeg-Hing
 */
public class SearchHandler implements EventHandler<ActionEvent>{
    
    private View view;
    private Presenter presenter;
    
    public SearchHandler(View view, Presenter presenter) {
        this.view = view;
        this.presenter = presenter;
    }

    @Override
    public void handle(ActionEvent t) {
        presenter.searchShortestPath(view.getOrigin(), view.getDestination());
    }
}

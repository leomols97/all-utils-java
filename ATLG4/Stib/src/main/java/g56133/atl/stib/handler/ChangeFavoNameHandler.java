/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g56133.atl.stib.handler;

import g56133.atl.stib.presenter.Presenter;
import g56133.atl.stib.view.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author Leong Paeg-Hing
 */
public class ChangeFavoNameHandler implements EventHandler<ActionEvent> {
    private View view;
    private Presenter presenter;
    
    public ChangeFavoNameHandler(View view, Presenter presenter) {
        this.view = view;
        this.presenter = presenter;
    }

    @Override
    public void handle(ActionEvent t) {
        String oldName = view.getFavorite();
        String newName = view.askfavoriteName();
        presenter.changeFavoName(oldName, newName);
    }
}

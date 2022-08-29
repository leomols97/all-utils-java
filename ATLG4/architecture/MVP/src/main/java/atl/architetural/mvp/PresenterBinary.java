/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atl.architetural.mvp;

import atl.observer.Observable;
import atl.observer.Observer;

/**
 *
 * @author Leong Paeg-Hing
 */
public class PresenterBinary implements Observer {

    private Model model;
    private ViewBinary view;

    public PresenterBinary(Model model, ViewBinary view) {
        System.out.println("DEBUG | PRESENTER  | Construction");
        this.model = model;
        this.view = view;
    }

    @Override
    public void update(Observable observable, Object arg) {
        Model savedModel = (Model) observable;
        int data = savedModel.getData();
        System.out.println("DEBUG | PRESENTER  | Mise à jour de l'observateur : l'entier vaut " + data);
        System.out.println("DEBUG | PRESENTER  | Ordonne à la vue de se mettre à jour : l'entier vaut " + data);        
        view.setNumber(data);
    }
}

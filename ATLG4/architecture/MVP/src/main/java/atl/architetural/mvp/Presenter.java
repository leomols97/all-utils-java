package atl.architetural.mvp;

import atl.observer.Observable;
import atl.observer.Observer;

/**
 *
 * @author jlc
 */
public class Presenter implements Observer {

    private Model model;
    private View view;

    public Presenter(Model model, View view) {
        System.out.println("DEBUG | PRESENTER  | Construction");
        this.model = model;
        this.view = view;
    }

    public void initialize() {
        System.out.println("DEBUG | PRESENTER  | Initialisation");
        model.initialize();
        int data = model.getData();
        view.initialize(data);
    }

    public void doSomething() {
        System.out.println("DEBUG | PRESENTER  | Reçoit une demande d'action");
        view.disableBouton();
        model.compute();
    }

    @Override
    public void update(Observable observable, Object arg) {
        System.out.println("DEBUG | PRESENTER  | Mise à jour de l'observateur : le cercle est jaune");
        System.out.println("DEBUG | PRESENTER  | Ordonne à la vue de se mettre à jour : le cercle est jaune");        
        view.setColorYellow();

        Model savedModel = (Model) observable;
        int data = savedModel.getData();
        System.out.println("DEBUG | PRESENTER  | Mise à jour de l'observateur : l'entier vaut " + data);
        System.out.println("DEBUG | PRESENTER  | Ordonne à la vue de se mettre à jour : l'entier vaut " + data);        
        view.setNumber(data);
    }
}

package atl.architetural.mvc;

import atl.observer.Observer;

/**
 *
 * @author jlc
 */
public class Controller {

    private Model model;
    private View view;

    public Controller(Model model, View view) {
        System.out.println("DEBUG | CONTROLLER | Construction");
        this.model = model;
        this.view = view;
    }

    public void addObserver(Observer o) {
        System.out.println("DEBUG | CONTROLLER | Ajoute le lien observateur-observé entre la vue et le modèle");
        model.addObserver(o);
    }

    public void initialize() {
        System.out.println("DEBUG | CONTROLLER | Initialisation");
        model.initialize();
        //int data = model.getData();
        //view.initialize(data);
    }

    public void doSomething() {
        System.out.println("DEBUG | CONTROLLER | Reçoit une demande d'action");
        view.disableBouton();
        model.compute();
        //la réponse du modele est traitée dans la méthode view.update
    }
}

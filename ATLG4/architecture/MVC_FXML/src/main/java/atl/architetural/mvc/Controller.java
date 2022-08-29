package atl.architetural.mvc;

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

    public void addObserver() {
        System.out.println("DEBUG | CONTROLLER | Ajoute le lien observateur-observé entre la vue et le modèle");
        model.addObserver(view);
    }

    public void initialize() {
        System.out.println("DEBUG | CONTROLLER | Initialisation");
        model.initialize();
        int data = model.getData();
        view.initialize(data);
    }

    public void doSomething() {
        System.out.println("DEBUG | CONTROLLER | Reçoit une demande d'action");
        view.disableBouton();
        model.calcul();
        //la réponse du modele est traitée dans la méthode view.update
    }
}

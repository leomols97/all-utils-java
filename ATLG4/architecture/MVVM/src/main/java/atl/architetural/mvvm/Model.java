package atl.architetural.mvvm;

import atl.observer.Observable;
import atl.observer.Observer;
import java.util.Random;

/**
 *
 * @author jlc
 */
public class Model extends Observable {

    private int data;
    private Random r;

    public Model() {
        System.out.println("DEBUG | MODEL      | Construction");
        r = new Random();
    }

    public void initialize() {
        System.out.println("DEBUG | MODEL      | Initialisation");
        this.data = 42;
    }

    public void compute() {
        System.out.println("DEBUG | MODEL      | Calcul commencé");
        this.data = r.nextInt(50);
        System.out.println("DEBUG | MODEL      | Calcul terminé");
        notifyObservers();
    }

    public int getData() {
        System.out.println("DEBUG | MODEL      | Demande des données");
        return data;
    }

    @Override
    public void notifyObservers() {
        super.notifyObservers(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addObserver(Observer observer) {
        super.addObserver(observer); //To change body of generated methods, choose Tools | Templates.
    }

}

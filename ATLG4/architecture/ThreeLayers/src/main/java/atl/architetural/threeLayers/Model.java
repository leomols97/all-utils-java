package atl.architetural.threeLayers;

import java.util.Random;

/**
 *
 * @author jlc
 */
public class Model {

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
    }

    public int getData() {
        System.out.println("DEBUG | MODEL      | Demande des données");
        return data;
    }

}

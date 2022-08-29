package atl.architetural.mvvm;

import atl.observer.Observable;
import atl.observer.Observer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author jlc
 */
public class ViewModel implements Observer {
    
    private Model model;
    
    private SimpleStringProperty value;
    private SimpleStringProperty styleProperty;
    private SimpleBooleanProperty buttonProperty;
    private SimpleStringProperty binary;
    
    public ViewModel(Model model) {
        System.out.println("DEBUG | VIEWMODEL  | Construction");
        this.model = model;
        this.value = new SimpleStringProperty();
        this.binary = new SimpleStringProperty();
        this.styleProperty = new SimpleStringProperty("-fx-fill:white");
        this.buttonProperty = new SimpleBooleanProperty(false);
    }
    
    public StringProperty getValue() {
        return value;
    }
    
    public StringProperty getBinary() {
        return binary;
    }
    
    public StringProperty getStyleProperty() {
        return styleProperty;
    }
    
    public BooleanProperty getButtonProperty() {
        return buttonProperty;
    }  
    
    @Override
    public void update(Observable observable, Object arg) {
        System.out.println("DEBUG | VIEWMODEL  | Mise à jour de l'observateur : le cercle est jaune");
        System.out.println("DEBUG | VIEWMODEL  | Ordonne à la vue de se mettre à jour : le cercle est jaune");
        styleProperty.setValue("-fx-fill:yellow");
        
        System.out.println("DEBUG | VIEWMODEL  | Mise à jour de l'observateur");
        Model savedModel = (Model) observable;
        int data = savedModel.getData();
        value.set("" + data);
        binary.set(Integer.toBinaryString(data));
    }
    
    public void doSomething() {
        System.out.println("DEBUG | VIEWMODEL  | Reçoit une demande d'action");
        buttonProperty.setValue(true);
        model.compute();
    }
    
    void initialize() {
        System.out.println("DEBUG | VIEWMODEL  | Initialisation");
        model.initialize();
        int data = model.getData();
        System.out.println("DEBUG | VIEWMODEL  | Pousse les valeurs dans la vues");
        value.set("" + data);
        styleProperty.setValue("-fx-fill:green");
    }

}

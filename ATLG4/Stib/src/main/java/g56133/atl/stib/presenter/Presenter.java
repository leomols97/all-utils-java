package g56133.atl.stib.presenter;

import g56133.atl.stib.view.View;
import g56133.atl.stib.model.core.Model;
import g56133.atl.stib.model.core.Node;
import g56133.atl.stib.model.core.Request;
import g56133.atl.stib.model.exception.RepositoryException;
import java.util.List;
import g56133.atl.stib.utils.Observer;

/**
 *
 * @author Leong Paeg-Hing
 */
public class Presenter implements Observer{
    
    private Model model;
    private View view;
    
    public Presenter(Model model, View view) {
        this.model = model;
        this.view = view;
    }
    
    public void initialiseSearchBox() {
        view.setUpStation(model.getStationName());
        view.setUpFavorites(model.getFavoris());
    }
    
    public void searchShortestPath(String origin, String destination) {
        try {
            model.search(origin, destination);
        } catch (RepositoryException ex) {
            // add pop up ?
        }
    }
    
    public void searchFavorite(String favorite) {
        try {
            this.model.applyFavorite(favorite);
        } catch (RepositoryException ex) {
            // add pop up ?
        }
    }
    
    public void addFavorite(String name, String origin, String destination) {
        try {
            this.model.addFavorite(name, origin, destination);
        } catch (RepositoryException ex) {
            // add pop up ?
        }
    }
    
    public void deleteFavorite(String name) {
        try {
            this.model.deleteFavorite(name);
        } catch (RepositoryException ex) {
            // add pop up ?
        }
    }
    
    public void changeFavoName(String oldName, String newName) {
        try {
            this.model.changeFavoName(oldName, newName);
        } catch (RepositoryException ex) {
            // add pop up ?
        }
    }
    
    public void changeFavoStations(String name, String origin, String destination) {
        try {
            this.model.changeFavoStations(name, origin, destination);
        } catch (RepositoryException ex) {
            // add pop up ?
        }
    }

    @Override
    public void update(List<Object> obj) {
        Request rq = (Request) obj.get(1);       
        switch(rq) {
            case ADD:
                String favorite = (String) obj.get(0);
                view.addFavorite(favorite);
                break;
            case DELETE: case NAMECHANGE:
                List<String> favUpdate = (List<String>) obj.get(0);
                view.updateFavoriteList(favUpdate);
                break;
            case SEARCH:
                Node destination = (Node) obj.get(0);
                view.displayPath(destination);
                break;
            case ERROR:
                String message = (String) obj.get(0);
                view.DisplayErrorMessage(message);
                break;
            case FAV:
                List<String> favStations = (List<String>) obj.get(0);
                view.setStations(favStations);
                break;
        }
    }
}

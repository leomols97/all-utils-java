package g56133.atl.stib.model.core;

import g56133.atl.stib.model.exception.RepositoryException;
import java.util.List;

/**
 *
 * @author Leong Paeg-Hing
 */
public interface Model {
    
    public void search(String origin, String destination) throws RepositoryException;
    
    public void applyFavorite(String favorite) throws RepositoryException;
    
    public void search(int origin, int destination) throws RepositoryException;
    
    public void addFavorite(String name, String origin, String destination) throws RepositoryException;
    
    public void deleteFavorite(String name) throws RepositoryException;
    
    public void changeFavoName(String oldName, String newName) throws RepositoryException;
    
    public void changeFavoStations(String name, String origin, String destination) throws RepositoryException;
    
    public List<String> getStationName();
    
    public List<String> getFavoris();
}

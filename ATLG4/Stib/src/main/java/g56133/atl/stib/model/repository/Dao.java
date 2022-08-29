package g56133.atl.stib.model.repository;

import g56133.atl.stib.model.dto.Dto;
import g56133.atl.stib.model.exception.RepositoryException;
import java.util.List;

/**
 *
 * @author Leong Paeg-Hing
 * @param <K> the type of the key
 * @param <T> the dto used to access the data base
 */
public interface Dao<K, T extends Dto<K>> {
    
    /**
     * Returns all the elements of the resource. This method can be long.
     *
     * @return all the elements of the resource.
     * @throws RepositoryException if the resource can't be accessed.
     */
    List<T> selectAll() throws RepositoryException;

    /**
     * Returns an element based on its key.
     *
     * @param key key of the element to select.
     * @return an element based on its key.
     * @throws RepositoryException if the resource can't be accessed.
     */
    T select(K key) throws RepositoryException;
}

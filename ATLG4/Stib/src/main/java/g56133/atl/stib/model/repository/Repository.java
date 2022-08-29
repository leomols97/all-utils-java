package g56133.atl.stib.model.repository;

import g56133.atl.stib.model.dto.Dto;
import g56133.atl.stib.model.exception.RepositoryException;
import java.util.List;

/**
 * @param <K> the key
 * @param <T> the dto used to acces the data base
 */
public interface Repository<K, T extends Dto<K>> {
    
    /**
     * Returns all the elements of the repository.
     *
     * @return all the elements of the repository.
     *
     * @throws RepositoryException if the repository can't access to the elements.
     */
    List<T> getAll() throws RepositoryException;

    /**
     * Return the element of the repository with the specific key.
     *
     * @param key key of the element.
     *
     * @return the element of the repository with the specific key.
     * @throws RepositoryException if the repository can't access to the element.
     */
    T get(K key) throws RepositoryException;
}

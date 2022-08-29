package g56133.mentoring.repository;

import java.util.List;

/**
 *
 * @author Leong Paeg-Hing
 * @param <T> generic type
 */
public interface Dao <T, K>{
    
    public void insert(T item) throws RepositoryException;
    
    public void delete(K key) throws RepositoryException;
    
    public void update(T item) throws RepositoryException;
    
    public T get(K key) throws RepositoryException;
    
    public List<T> getAll() throws RepositoryException;
}

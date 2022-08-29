package g56133.mentoring.repository;

import java.util.List;

/**
 *
 * @author Leong Paeg-Hing
 * @param <T> generic type
 */
public interface Repository <K, T>{
    
    public void add(T item) throws RepositoryException;
    
    public void remove(K key) throws RepositoryException;
    
    public T get(K key) throws RepositoryException;
    
    public List<T> getAll() throws RepositoryException;
    
    public boolean contains(K key) throws RepositoryException;
}

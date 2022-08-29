package g56133.mentoring.repository;

import g56133.atl.Mentoring.dto.StudentDto;
import java.util.List;

/**
 *
 * @author Leong Paeg-Hing
 */
public class StudentRepository implements Repository<Integer, StudentDto> {

    private final StudentDao dao;

    /**
     * Creates a new instance of <code>StudentRepository</code> with a
     * compatible <code>StudentDao</code>.
     */
    public StudentRepository() {
        dao = StudentDao.getInstance();
    }

    /**
     * Creates a new instance of <code>StudentRepository</code>. The compatible
     * <code>StudentDao</code> has to be given. Useful for the unit test.
     *
     * @param dao compatible <code>StudentDao</code>.
     */
    StudentRepository(StudentDao dao) {
        this.dao = dao;
    }

    @Override
    public void add(StudentDto item) throws RepositoryException{
        if(item == null) {
            throw new RepositoryException("The item can't be null");
        }else if (contains(item.getKey())) {
            dao.update(item);
        } else {
             dao.insert(item);
        }
    }

    @Override
    public void remove(Integer key) throws RepositoryException{
        if(key == null) {
            throw new RepositoryException("The item can't be null");
        }else if (!contains(key))
            throw new RepositoryException("You can't delete a student that doesn't exist.");
        else
            dao.delete(key);
    }

    @Override
    public List<StudentDto> getAll() throws RepositoryException{
        return dao.getAll();
    }

    @Override
    public StudentDto get(Integer key) throws RepositoryException{
        return dao.get(key);
    }

    @Override
    public boolean contains(Integer key) throws RepositoryException{
        System.out.println("wow");
        return dao.get(key) != null;
    }
}

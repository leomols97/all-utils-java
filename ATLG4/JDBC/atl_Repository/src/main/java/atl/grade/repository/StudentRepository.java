package atl.grade.repository;

import atl.grade.dto.StudentDto;
import atl.grade.exception.RepositoryException;
import atl.grade.jdbc.StudentsDao;
import java.util.List;

/**
 *
 * @author jlc
 */
public class StudentRepository implements Repository<Integer, StudentDto> {

    private final StudentsDao dao;

    public StudentRepository() throws RepositoryException {
        dao = StudentsDao.getInstance();
    }

    StudentRepository(StudentsDao dao) {
        this.dao = dao;
    }

    @Override
    public Integer add(StudentDto item) throws RepositoryException {
        Integer key = item.getKey();
        if (contains(item.getKey())) {
            dao.update(item);
        } else {
            key = dao.insert(item);
        }
        return key;
    }

    @Override
    public void remove(Integer key) throws RepositoryException {
        dao.delete(key);
    }

    @Override
    public List<StudentDto> getAll() throws RepositoryException {
        return dao.selectAll();
    }

    @Override
    public StudentDto get(Integer key) throws RepositoryException {
        StudentDto refreshItem = dao.select(key);
        return refreshItem;
    }

    @Override
    public boolean contains(Integer key) throws RepositoryException {
        StudentDto refreshItem = dao.select(key);
        return refreshItem != null;
    }

}

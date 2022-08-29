package g56133.atl.stib.model.repository;

import g56133.atl.stib.model.JDBC.StopsDao;
import g56133.atl.stib.model.dto.StopDto;
import g56133.atl.stib.model.exception.RepositoryException;
import java.util.List;
import javafx.util.Pair;

/**
 *
 * @author Leong Paeg-Hing
 */
public class StopRepository implements Repository<Pair<Integer, Integer>, StopDto> {

    private final StopsDao dao;

    public StopRepository() throws RepositoryException {
        dao = StopsDao.getInstance();
    }

    StopRepository(StopsDao dao) {
        this.dao = dao;
    }
    
    @Override
    public List<StopDto> getAll() throws RepositoryException {
        return dao.selectAll();
    }
    
    public List<StopDto> getAllWithName() throws RepositoryException {
        return dao.selectAllWithName();
    } 

    @Override
    public StopDto get(Pair<Integer, Integer> key) throws RepositoryException {
        StopDto refreshItem = dao.select(key);
        return refreshItem;
    }
    
}

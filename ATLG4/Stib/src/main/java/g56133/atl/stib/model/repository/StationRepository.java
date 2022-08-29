package g56133.atl.stib.model.repository;

import g56133.atl.stib.model.JDBC.StationsDao;
import g56133.atl.stib.model.dto.StationDto;
import g56133.atl.stib.model.exception.RepositoryException;
import java.util.List;

/**
 *
 * @author Leong Paeg-Hing
 */
public class StationRepository implements Repository<Integer, StationDto> {
    
    private final StationsDao dao;

    public StationRepository() throws RepositoryException {
        dao = StationsDao.getInstance();
    }

    StationRepository(StationsDao dao) {
        this.dao = dao;
    }

    @Override
    public List<StationDto> getAll() throws RepositoryException {
        return dao.selectAll();
    }

    @Override
    public StationDto get(Integer key) throws RepositoryException {
        StationDto refreshItem = dao.select(key);
        return refreshItem;
    }
    
    public StationDto get(String key) throws RepositoryException {
        StationDto refreshItem = dao.select(key);
        return refreshItem;
    }  
}

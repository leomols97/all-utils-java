package g56133.atl.stib.model.repository;

import g56133.atl.stib.model.JDBC.LinesDao;
import g56133.atl.stib.model.dto.LineDto;
import g56133.atl.stib.model.exception.RepositoryException;
import java.util.List;

/**
 *
 * @author Leong Paeg-Hing
 */
public class LineRepository implements Repository<Integer, LineDto> {
    
    private final LinesDao dao;

    public LineRepository() throws RepositoryException {
        dao = LinesDao.getInstance();
    }

    LineRepository(LinesDao dao) {
        this.dao = dao;
    }

    @Override
    public List<LineDto> getAll() throws RepositoryException {
        return dao.selectAll();
    }

    @Override
    public LineDto get(Integer key) throws RepositoryException {
        LineDto refreshItem = dao.select(key);
        return refreshItem;
    }
}

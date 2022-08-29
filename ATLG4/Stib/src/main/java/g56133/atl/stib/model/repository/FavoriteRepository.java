/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g56133.atl.stib.model.repository;

import g56133.atl.stib.model.JDBC.FavoritesDao;
import g56133.atl.stib.model.dto.FavoriteDto;
import g56133.atl.stib.model.exception.RepositoryException;
import java.util.List;

/**
 *
 * @author Leong Paeg-Hing
 */
public class FavoriteRepository implements Repository<String, FavoriteDto> {
    
    private final FavoritesDao dao;

    public FavoriteRepository() throws RepositoryException {
        dao = FavoritesDao.getInstance();
    }

    FavoriteRepository(FavoritesDao dao) {
        this.dao = dao;
    }

    @Override
    public List<FavoriteDto> getAll() throws RepositoryException {
        return dao.selectAll();
    }

    @Override
    public FavoriteDto get(String key) throws RepositoryException {
        return dao.select(key);
    }
    
    public void insert(FavoriteDto item) throws RepositoryException {
        if(contain(item.getKey())) {
            dao.update(item);
        }else {
            dao.insert(item);
        }
    }
    
    public boolean contain(String key) throws RepositoryException {
        FavoriteDto item = dao.select(key);
        return item != null;
    }
    
    public void delete(String key) throws RepositoryException {
        dao.delete(key);
    }
    
}

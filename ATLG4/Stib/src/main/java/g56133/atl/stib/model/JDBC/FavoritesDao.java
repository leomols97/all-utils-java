/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g56133.atl.stib.model.JDBC;

import g56133.atl.stib.model.dto.FavoriteDto;
import g56133.atl.stib.model.dto.LineDto;
import g56133.atl.stib.model.dto.StationDto;
import g56133.atl.stib.model.exception.RepositoryException;
import g56133.atl.stib.model.repository.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leong Paeg-Hing
 */
public class FavoritesDao implements Dao<String, FavoriteDto> {
    
    private Connection connexion;

    private FavoritesDao() throws RepositoryException {
        connexion = DBManager.getInstance().getConnection();
    }
    
    public static FavoritesDao getInstance() throws RepositoryException {
        return FavoritesDaoHolder.getInstance();
    }

    @Override
    public List<FavoriteDto> selectAll() throws RepositoryException {
        String sql = "SELECT name, origin, destination FROM FAVORITES";
        List<FavoriteDto> dtos = new ArrayList<>();
        try (Statement stmt = connexion.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                FavoriteDto dto = new FavoriteDto(rs.getString(1), rs.getInt(2), rs.getInt(3));
                dtos.add(dto);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dtos;
    }

    @Override
    public FavoriteDto select(String key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("No key has been given");
        }
        String sql = "SELECT name, origin, destination FROM FAVORITES WHERE name = ?";
        FavoriteDto dto = null;
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, key);
            ResultSet rs = pstmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                dto = new FavoriteDto(rs.getString(1), rs.getInt(2), rs.getInt(3));
                count++;
            }
            if (count > 1) {
                throw new RepositoryException("Not unique record " + key);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dto;
    }
    
    public void insert(FavoriteDto item) throws RepositoryException {
        if (item == null) {
            throw new RepositoryException("Aucune élément donné en paramètre");
        }
        String sql = "INSERT INTO FAVORITES(name, origin, destination) values(?, ?, ?)";
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, item.getKey());
            pstmt.setInt(2, item.getOrigin());
            pstmt.setInt(3, item.getDestintion());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }
    
    public void update(FavoriteDto item) throws RepositoryException {
        if (item == null) {
            throw new RepositoryException("Aucune élément donné en paramètre");
        }
        String sql = "UPDATE FAVORITES SET origin=? ,destination=? where name=? ";
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setInt(1, item.getOrigin());
            pstmt.setInt(2, item.getDestintion());
            pstmt.setString(3, item.getKey());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
    }
    
    public void delete(String key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("Aucune clé donnée en paramètre");
        }
        String sql = "DELETE FROM FAVORITES WHERE name = ?";
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, key);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }
    
    private static class FavoritesDaoHolder {

        private static FavoritesDao getInstance() throws RepositoryException {
            return new FavoritesDao();
        }
    }
    
}

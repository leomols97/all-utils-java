package g56133.atl.stib.model.JDBC;

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
public class StationsDao implements Dao<Integer, StationDto> {
    
    private Connection connexion;

    private StationsDao() throws RepositoryException {
        connexion = DBManager.getInstance().getConnection();
    }
    
    public static StationsDao getInstance() throws RepositoryException {
        return StationsDaoHolder.getInstance();
    }

    @Override
    public List<StationDto> selectAll() throws RepositoryException {
        String sql = "SELECT id, name FROM STATIONS ORDER BY id";
        List<StationDto> dtos = new ArrayList<>();
        try (Statement stmt = connexion.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                StationDto dto = new StationDto(rs.getInt(1), rs.getString(2));
                dtos.add(dto);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dtos;
    }

    @Override
    public StationDto select(Integer key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("No key has been given");
        }
        String sql = "SELECT id, name FROM STATIONS WHERE id = ?";
        StationDto dto = null;
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setInt(1, key);
            ResultSet rs = pstmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                dto = new StationDto(rs.getInt(1), rs.getString(2));
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
    
    public StationDto select(String key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("No key has been given");
        }
        String sql = "SELECT id FROM STATIONS WHERE name = ?";
        StationDto dto = null;
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, key);
            ResultSet rs = pstmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                dto = new StationDto(rs.getInt(1), null);
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
    
    private static class StationsDaoHolder {

        private static StationsDao getInstance() throws RepositoryException {
            return new StationsDao();
        }
    }
}

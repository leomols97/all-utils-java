package g56133.atl.stib.model.JDBC;

import g56133.atl.stib.model.dto.StopDto;
import g56133.atl.stib.model.exception.RepositoryException;
import g56133.atl.stib.model.repository.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

/**
 *
 * @author Leong Paeg-Hing
 */
public class StopsDao implements Dao<Pair<Integer, Integer>, StopDto> {
    
    private Connection connexion;

    private StopsDao() throws RepositoryException {
        connexion = DBManager.getInstance().getConnection();
    }
    
    public static StopsDao getInstance() throws RepositoryException {
        return StopsDaoHolder.getInstance();
    }

    @Override
    public List<StopDto> selectAll() throws RepositoryException {
        String sql = "SELECT id_line, id_station, id_order FROM STOPS "
                + "ORDER by id_line, id_order";
        List<StopDto> dtos = new ArrayList<>();
        try (Statement stmt = connexion.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                StopDto dto = new StopDto(rs.getInt(1), rs.getInt(2), 
                        rs.getInt(3));
                dtos.add(dto);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dtos;
    }
    
    public List<StopDto> selectAllWithName() throws RepositoryException {
        String sql = "SELECT id_line, id_station, id_order, name FROM STOPS "
                + "JOIN STATIONS on id_station = id ORDER by id_line, id_order";
        List<StopDto> dtos = new ArrayList<>();
        try (Statement stmt = connexion.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                StopDto dto = new StopDto(rs.getInt(1), rs.getInt(2), 
                        rs.getInt(3), rs.getString(4));
                dtos.add(dto);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dtos;
    }

    @Override
    public StopDto select(Pair<Integer, Integer> key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("No key has been given");
        }
        String sql = "SELECT id_line, id_station, id_order FROM STOPS WHERE "
                + "id_line = ? AND id_station = ?";
        StopDto dto = null;
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setInt(1, key.getKey());
            pstmt.setInt(2, key.getValue());
            ResultSet rs = pstmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                dto = new StopDto(rs.getInt(1), rs.getInt(2), rs.getInt(3));
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
    
    private static class StopsDaoHolder {

        private static StopsDao getInstance() throws RepositoryException {
            return new StopsDao();
        }
    }
}

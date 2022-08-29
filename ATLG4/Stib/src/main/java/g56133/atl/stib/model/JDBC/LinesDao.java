package g56133.atl.stib.model.JDBC;

import g56133.atl.stib.model.dto.LineDto;
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
public class LinesDao implements Dao<Integer, LineDto> {
    
    private Connection connexion;

    private LinesDao() throws RepositoryException {
        connexion = DBManager.getInstance().getConnection();
    }
    
    public static LinesDao getInstance() throws RepositoryException {
        return LinesDaoHolder.getInstance();
    }

    @Override
    public List<LineDto> selectAll() throws RepositoryException {
        String sql = "SELECT id FROM LINES";
        List<LineDto> dtos = new ArrayList<>();
        try (Statement stmt = connexion.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                LineDto dto = new LineDto(rs.getInt(1));
                dtos.add(dto);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dtos;
    }

    @Override
    public LineDto select(Integer key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("No key has been given");
        }
        String sql = "SELECT id FROM LINES WHERE id = ?";
        LineDto dto = null;
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setInt(1, key);
            ResultSet rs = pstmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                dto = new LineDto(rs.getInt(1));
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
    
    private static class LinesDaoHolder {

        private static LinesDao getInstance() throws RepositoryException {
            return new LinesDao();
        }
    }
    
}

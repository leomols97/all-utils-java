package atl.grade.prepare;

import atl.grade.Demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jlc
 */
public class DemoPrepare extends Demo {

    private final String url;
    private final int currentId;
    private final String currentName;

    public DemoPrepare(String url, int currentId, String currentName) {
        this.url = url;
        this.currentId = currentId;
        this.currentName = currentName;
    }

    @Override
    public void execute(String url) {
        try {
            Connection connexion = DriverManager.getConnection("jdbc:sqlite:" + url);

            String query = "SELECT id,lastname,firstname  FROM STUDENTS WHERE id = ? AND lastname like ? ";
            PreparedStatement stmt = connexion.prepareStatement(query);

            stmt.setInt(1, currentId);
            stmt.setString(2, currentName + "%");

            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                int id = result.getInt("id");
                String firstname = result.getString("firstname");
                String lastname = result.getString("lastname");
                System.out.println("\t record : " + id + " " + firstname + " " + lastname);
            }
        } catch (SQLException ex) {
            System.out.println("DEMO_PREPARE | Erreur " + ex.getMessage() + " SQLState " + ex.getSQLState());
        }
    }

    @Override
    public String getTitle() {
        return "Requête préparée";
    }
}

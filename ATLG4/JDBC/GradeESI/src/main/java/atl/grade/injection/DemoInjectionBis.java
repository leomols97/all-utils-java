package atl.grade.injection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import atl.grade.Demo;
import java.sql.ResultSet;

/**
 *
 * @author jlc
 */
public class DemoInjectionBis extends Demo {

    private final String url;
    private final int currentId;
    private final String currentName;

    public DemoInjectionBis(String url, int currentId, String currentName) {
        this.url = url;
        this.currentId = currentId;
        this.currentName = currentName;
    }

    @Override
    public void execute(String url) {
        try {
            Connection connexion = DriverManager.getConnection("jdbc:sqlite:" + url);
            Statement stmt = connexion.createStatement();

            String query = "SELECT id, lastname, firstname FROM STUDENTS WHERE id = "
                    + currentId + " AND lastname like '" + currentName + "%'";

            ResultSet result = stmt.executeQuery(query);

            while (result.next()) {
                int id = result.getInt("id");
                String firstname = result.getString("firstname");
                String lastname = result.getString("lastname");
                System.out.println("\t record : " + id + " " + firstname + " " + lastname);
            }

        } catch (SQLException ex) {
            System.out.println("DEMO_UPDATE | Erreur " + ex.getMessage() + " SQLState " + ex.getSQLState());
        }
    }

    @Override
    public String getTitle() {
        return "Sécurité : Test des injections SQL";
    }
}

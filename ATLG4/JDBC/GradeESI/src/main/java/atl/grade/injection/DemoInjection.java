package atl.grade.injection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import atl.grade.Demo;

/**
 *
 * @author jlc
 */
public class DemoInjection extends Demo {

    private final String query;

    public DemoInjection(String query) {
        this.query = query;
    }

    @Override
    public void execute(String url) {
        try {
            Connection connexion = DriverManager.getConnection("jdbc:sqlite:" + url);
            Statement stmt = connexion.createStatement();

            int count = stmt.executeUpdate(query);
            System.out.println("\t Nombre de record modifié : " + count);

        } catch (SQLException ex) {
            System.out.println("DEMO_UPDATE | Erreur " + ex.getMessage() + " SQLState " + ex.getSQLState());
        }
    }

    @Override
    public String getTitle() {
        return "Sécurité : Test des injections SQL";
    }
}

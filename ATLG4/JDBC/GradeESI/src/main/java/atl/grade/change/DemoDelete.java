package atl.grade.change;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import atl.grade.Demo;

/**
 *
 * @author jlc
 */
public class DemoDelete extends Demo {

    @Override
    public void execute(String url) {
        try {
            Connection connexion = DriverManager.getConnection("jdbc:sqlite:" + url);
            Statement stmt = connexion.createStatement();

            String query = "DELETE FROM STUDENTS WHERE firstname ='SpongeBob'";

            int count = stmt.executeUpdate(query);
            System.out.println("\t Nombre de record modifié : " + count);

        } catch (SQLException ex) {
            System.out.println("DEMO_DELETE | Erreur " + ex.getMessage() + " SQLState " + ex.getSQLState());
        }
    }

    @Override
    public String getTitle() {
        return "Suppression d'un utilisateur dans la DB";
    }
}

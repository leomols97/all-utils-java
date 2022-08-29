package atl.grade.selection;

import atl.grade.Demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jlc
 */
public class DemoSelect extends Demo {

    @Override
    public void execute(String url) {
        try {
            Connection connexion = DriverManager.getConnection("jdbc:sqlite:" + url);
            Statement stmt = connexion.createStatement();

            String query = "SELECT id,lastname,firstname FROM STUDENTS WHERE firstname ='Phoebe'";

            ResultSet result = stmt.executeQuery(query);

            while (result.next()) {
                int id = result.getInt(1);
                String lastname = result.getString(2);
                String firstname = result.getString(3);
                System.out.println("\t record : " + id + " " + firstname + " " + lastname);
            }
        } catch (SQLException ex) {
            System.out.println("DEMO_SELECT | Erreur " + ex.getMessage() + " SQLState " + ex.getSQLState());
        }
    }

    @Override
    public String getTitle() {
        return "Recherche d'un utilisateur spécifique en DB";
    }
}

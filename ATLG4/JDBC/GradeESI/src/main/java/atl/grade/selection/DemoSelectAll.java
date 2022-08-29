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
public class DemoSelectAll extends Demo {

    @Override
    public void execute(String url) {
        try {
            Connection connexion = DriverManager.getConnection("jdbc:sqlite:" + url);
            Statement stmt = connexion.createStatement();

            String query = "SELECT id,firstname,lastname FROM STUDENTS";

            ResultSet result = stmt.executeQuery(query);

            while (result.next()) {
                int id = result.getInt("id");
                String firstname = result.getString("firstname");
                String lastname = result.getString("lastname");
                System.out.println("\t record : " + id + " " + firstname + " " + lastname);
            }
        } catch (SQLException ex) {
            System.out.println("DEMO_SELECT_ALL | Erreur " + ex.getMessage() + " SQLState " + ex.getSQLState());
        }
    }

    @Override
    public String getTitle() {
        return "La liste des utilisateurs en DB";
    }
}

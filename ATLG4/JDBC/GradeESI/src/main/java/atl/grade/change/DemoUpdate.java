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
public class DemoUpdate extends Demo{
    @Override
    public void execute(String url) {
        try {
            Connection connexion = DriverManager.getConnection("jdbc:sqlite:" + url);
            Statement stmt = connexion.createStatement();

            String query = "UPDATE STUDENTS SET firstname='Patrick',lastName='Star' where id=1 ";

            int count = stmt.executeUpdate(query);
            System.out.println("\t Nombre de record modifié : " + count);
            
        } catch (SQLException ex) {
            System.out.println("DEMO_UPDATE | Erreur " + ex.getMessage() + " SQLState " + ex.getSQLState());
        }
    }
    
        @Override
    public String getTitle() {
        return "Mise à jour d'un utilisateur dans la DB";
    }
}

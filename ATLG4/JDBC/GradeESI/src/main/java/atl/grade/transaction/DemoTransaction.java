package atl.grade.transaction;

import atl.grade.Demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jlc
 */
public class DemoTransaction extends Demo {

    @Override
    public void execute(String url) {
        Connection connexion = null;
        try {
            connexion = DriverManager.getConnection("jdbc:sqlite:" + url);
            Statement stmt = connexion.createStatement();
            connexion.setAutoCommit(false);

            String query = "UPDATE STUDENTS SET firstname='Sandy' where id=2 ";

            int count = stmt.executeUpdate(query);
            System.out.println("\t Nombre de record modifié : " + count);

            query = "UPDATE ERREUR SET name='Sandy' where id=1 ";

            count = stmt.executeUpdate(query);
            System.out.println("\t Nombre de record modifié : " + count);

            connexion.commit();

        } catch (SQLException | IllegalArgumentException ex) {
            System.out.println("DEMO_TRANSACTION | Erreur " + ex.getMessage());
            try {
                connexion.rollback();
            } catch (SQLException ex1) {
                System.out.println("DEMO_TRANSACTION | Erreur du rollback " + ex1.getMessage() + " SQLState " + ex1.getSQLState());
            }
        }
    }

    @Override
    public String getTitle() {
        return "Test d'une transaction en erreur";
    }
}

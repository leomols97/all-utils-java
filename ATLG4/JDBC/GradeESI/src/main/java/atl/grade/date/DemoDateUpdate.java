package atl.grade.date;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import atl.grade.Demo;

/**
 *
 * @author jlc
 */
public class DemoDateUpdate extends Demo{

    @Override
    public void execute(String url) {
        try {
            Connection connexion = DriverManager.getConnection("jdbc:sqlite:" + url);
            Statement stmt = connexion.createStatement();

            LocalDateTime localLast = LocalDateTime.now();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            String formatDateTime = localLast.format(formatter);

            String query = "UPDATE GRADES SET dateModified='" + formatDateTime + "'";

            int count = stmt.executeUpdate(query);
            System.out.println("\t Nombre de record modifié : " + count);

        } catch (SQLException ex) {
            System.out.println("DEMO_DATE_UPDATE | Erreur " + ex.getMessage() + " SQLState " + ex.getSQLState());
        }
    }
    
        @Override
    public String getTitle() {
        return "Mises à jour des Dates et des Timestamp";
    }
}

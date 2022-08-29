package atl.grade.date;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import atl.grade.Demo;

/**
 *
 * @author jlc
 */
public class DemoDateSelect extends Demo{

    @Override
    public void execute(String url) {
        try {
            Connection connexion = DriverManager.getConnection("jdbc:sqlite:" + url);
            Statement stmt = connexion.createStatement();

            String query = "SELECT score, date, dateModified FROM GRADES";

            ResultSet result = stmt.executeQuery(query);

            while (result.next()) {
                int id = result.getInt("score");
                String dateText = result.getString("date");
                String modifiedText = result.getString("dateModified");
                //Conversion d'un String en LocalDate, format standard
                LocalDate date = LocalDate.parse(dateText);
                
                //Conversion d'un String en LocalDateTime, format non-standard
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime modified = LocalDateTime.parse(modifiedText,formatter);
                System.out.println("\t record : " + id + " " + date + " " + modified);
            }
        } catch (SQLException ex) {
            System.out.println("DEMO_DATE_SELECT | Erreur " + ex.getMessage() + " SQLState " + ex.getSQLState());
        }
    }
    
        @Override
    public String getTitle() {
        return "Lecture des Dates et des Timestamp";
    }
}

package atl.grade.join;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import atl.grade.Demo;

/**
 *
 * @author jlc
 */
public class DemoJoin extends Demo {

    @Override
    public void execute(String url) {
        try {
            Connection connexion = DriverManager.getConnection("jdbc:sqlite:" + url);
            Statement stmt = connexion.createStatement();

            String query = "SELECT S.firstname,S.lastname,G.id_lesson,G.score "
                    + "FROM GRADES G LEFT JOIN STUDENTS S ON G.id_student = S.id";

            ResultSet result = stmt.executeQuery(query);

            while (result.next()) {
                String firstname = result.getString(1);
                String lastname = result.getString(2);
                String lesson = result.getString(3);
                int score = result.getInt(4);
                System.out.println("\t record : " + firstname + " " + lastname + " " + lesson + " " + score);
            }
        } catch (SQLException ex) {
            System.out.println("DEMO_JOIN | Erreur " + ex.getMessage() + " SQLState " + ex.getSQLState());
        }
    }

    @Override
    public String getTitle() {
        return "Lecture de tables jointes";
    }
}

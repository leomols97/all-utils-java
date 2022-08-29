/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atl.grade.selection;

import atl.grade.Demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Leong Paeg-Hing
 */
public class SelectAllLesson extends Demo {

    @Override
    public void execute(String url) {
        try {
            Connection connexion = DriverManager.getConnection("jdbc:sqlite:" + url);
            Statement stmt = connexion.createStatement();

            String query = "SELECT acronym FROM LESSONS";

            ResultSet result = stmt.executeQuery(query);

            while (result.next()) {
                String acronym = result.getString("acronym");
                System.out.println("\t record : " + acronym);
            }
        } catch (SQLException ex) {
            System.out.println("DEMO_SELECT_ALL | Erreur " + ex.getMessage() + " SQLState " + ex.getSQLState());
        }
    }

    @Override
    public String getTitle() {
        return "La liste des lecons en DB";
    }
}

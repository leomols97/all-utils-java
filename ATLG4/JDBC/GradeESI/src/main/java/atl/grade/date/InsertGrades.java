/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atl.grade.date;

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
public class InsertGrades extends Demo {

    @Override
    public void execute(String url) {
        try {
            Connection connexion = DriverManager.getConnection("jdbc:sqlite:" + url);
            Statement stmt = connexion.createStatement();

            String query = "INSERT INTO GRADES(score, date, dateModified, "
                    + "id_student, id_lesson) values(18, '2022-04-08', "
                    + "2022-04-08 20:07:30.000, 1, 'ATL')";

            int count = stmt.executeUpdate(query);
            System.out.println("\t Ajout dans GRADES.");
            System.out.println("\t Nombre de record modifié : " + count);

            ResultSet result = stmt.getGeneratedKeys();
            while (result.next()) {
                int id = result.getInt(1);
                System.out.println("\t clé ajoutée : " + id);
            }
        } catch (SQLException ex) {
            System.out.println("DEMO_INSERT | Erreur " + ex.getMessage() + " SQLState " + ex.getSQLState());
        }
    }

    @Override
    public String getTitle() {
        return "Insertion d'un utilisateur dans la DB";
    }
}
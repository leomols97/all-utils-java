/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atl.grade.change;

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
public class InsertDelete extends Demo {

    @Override
    public void execute(String url) {
        try {
            Connection connexion = DriverManager.getConnection("jdbc:sqlite:" + url);
            Statement stmt = connexion.createStatement();

            insertANLL(stmt);
            System.out.println("");
            getAll(stmt);
            System.out.println("");
            deleteANLL(stmt);
            System.out.println("");
            getAll(stmt);
        } catch (SQLException ex) {
            System.out.println("DEMO_INSERT | Erreur " + ex.getMessage() + " SQLState " + ex.getSQLState());
        }
    }
    
    public void insertANLL(Statement stmt) throws SQLException{
        String query = "INSERT INTO LESSONS(acronym) values('ANLL')";

        int count = stmt.executeUpdate(query);
        System.out.println("\t Insertion de ANLL dans la DB.");
        System.out.println("\t Nombre de record modifié : " + count);

        ResultSet result = stmt.getGeneratedKeys();
        while (result.next()) {
            int id = result.getInt(1);
            System.out.println("\t clé ajoutée : " + id);
        }
    }
    
    public void deleteANLL(Statement stmt) throws SQLException{
        String query = "DELETE FROM LESSONS WHERE acronym ='ANLL'";

        int count = stmt.executeUpdate(query);
        System.out.println("\t Suppression de ANLL de la DB.");
        System.out.println("\t Nombre de record modifié : " + count);
    }
    
    public void getAll(Statement stmt) throws SQLException {
        String query = "SELECT * FROM LESSONS";

        ResultSet result = stmt.executeQuery(query);

        while (result.next()) {
            String acronym = result.getString(1);
            System.out.println("\t record : " + acronym);
        }
    }

    @Override
    public String getTitle() {
        return "Insertion et suppression de ANLL dans la DB";
    }
}

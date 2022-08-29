/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monTableView;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Leong Paeg-Hing
 */
public class TableViewController {

    @FXML
    private TextField ID;

    @FXML
    private TextField Prenom;

    @FXML
    private TextField Nom;

    @FXML
    private TableView<Student> myTable;

    @FXML
    private TableColumn<Student, Integer> colNum;

    @FXML
    private TableColumn<Student, Integer> colPrenom;

    @FXML
    private TableColumn<Student, Integer> colNom;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (this.ID.getText().equalsIgnoreCase("") && this.Prenom.getText().equalsIgnoreCase("")
                && !this.Nom.getText().equalsIgnoreCase("")) {
            int id = Integer.parseInt(this.ID.getText());
            String prenom = this.Prenom.getText();
            String nom = this.Nom.getText();
            Student tmpStudent = new Student(id, prenom, nom);
            this.myTable.getItems().add(tmpStudent);
        }
    }

    public TableViewController() {
        System.out.println("wtf");
    }

    public void initialize() {
        this.colNum.setCellValueFactory(new PropertyValueFactory<>("num"));
        this.colPrenom.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        this.colNom.setCellValueFactory(new PropertyValueFactory<>("lastname"));
    }
}

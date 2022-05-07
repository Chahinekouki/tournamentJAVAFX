/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.sponsor;

import com.jfoenix.controls.JFXTextField;
import entities.Sponsor;
import utils.MyDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AddSponsorController implements Initializable {

    private JFXTextField nameFld;
    

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Sponsor sponsor = null;
    private boolean update;
    int sponsorId;
    @FXML
    private JFXTextField nomFld;
    @FXML
    private JFXTextField numFld;
    @FXML
    private JFXTextField budgetFld;
    
    @FXML
    private JFXTextField imageFld;
     ObservableList<Sponsor>  SponsorList = FXCollections.observableArrayList();
   

    /**
     * Initializes the controller class.
     */
    int categorieID = 1;
    boolean flash=true;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
   
        }
    
 
    @FXML
    private void save(MouseEvent event) throws SQLException {

        connection = MyDB.getInstance().getConnexion();
       
        //tsajalhom
        
        String nom = nomFld.getText();
        String num = numFld.getText();
        String budget = budgetFld.getText();
        String image = imageFld.getText();
        

        if (nom.isEmpty()||num.isEmpty()||budget.isEmpty()||image.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Remplissez les champs!");
            alert.showAndWait();

        } else {
            
            getQuery();
            insert();
            clean();
            

        }

    }

    @FXML
    private void clean() {
        nomFld.setText(null);
        numFld.setText(null);
        budgetFld.setText(null);
        imageFld.setText(null);
        
        
    }

    private void getQuery() {

        if (update == false) {
            
            query = "INSERT INTO `sponsors`(`nom`, `num`,`budget`,`image` ) VALUES (?,?,?,?)";

        }else{
            query = "UPDATE `sponsors` SET "
                    + "`nom`=?,"
                    + "`num`=?,"
                    + "`budget`=?,"
                    + "`image`=? WHERE id = '"+sponsorId+"'";
        }

    }

    private void insert() throws SQLException {

        try {
            
            
            preparedStatement = connection.prepareStatement(query);
            
             
            
            preparedStatement.setString(1, nomFld.getText());
            preparedStatement.setInt(2, Integer.parseInt(numFld.getText()));
            preparedStatement.setFloat(3, Float.parseFloat(budgetFld.getText()));
            preparedStatement.setString(4, imageFld.getText());
           
            preparedStatement.execute();
            

        } catch (SQLException ex) {
            Logger.getLogger(AddSponsorController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void setTextField(int id, String nom, int num,float budget, String image) {

        sponsorId = id;
        nomFld.setText(nom);
        numFld.setText(Float.toString(num));
        budgetFld.setText(Float.toString(budget));
        imageFld.setText(image);
        
        

    }

    void setUpdate(boolean b) {
        this.update = b;

    }

}
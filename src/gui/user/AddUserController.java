/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.user;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entities.User;
import utils.MyDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author hocin
 */
public class AddUserController implements Initializable {

    private JFXTextField nameFld;
    

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    User user = null;
    private boolean update;
    int userId;
    @FXML
    private JFXTextField usernameFld;
    @FXML
    private JFXTextField emailFld;
    @FXML
    private JFXTextField numTelFld;
    @FXML
    private JFXTextField cinPassportFld;
    
    
   

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
        
        String username = usernameFld.getText();
        String email = emailFld.getText();
        String numTel = numTelFld.getText();
        String cinPassport = cinPassportFld.getText();

        if (username.isEmpty()||email.isEmpty()||numTel.isEmpty()||cinPassport.isEmpty()) {
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
        usernameFld.setText(null);
        emailFld.setText(null);
        numTelFld.setText(null);
        cinPassportFld.setText(null);
        
        
    }

    private void getQuery() {

        if (update == false) {
            
            query = "INSERT INTO `user1`(`username`,`email`, `num_tel`,`cin_passport` ) VALUES (?,?,?,?)";

        }else{
            query = "UPDATE `user` SET "
                    + "`username`=?,"
                    + "`email`=?,"
                    + "`num_tel`=?,"
                    + "`cin_passport`=? WHERE id = '"+userId+"'";
        }

    }

    private void insert() throws SQLException {

        try {
            
            
            preparedStatement = connection.prepareStatement(query);
            
             
            
            preparedStatement.setString(1, usernameFld.getText());
            preparedStatement.setString(2, emailFld.getText());
            preparedStatement.setString(3, numTelFld.getText());
            preparedStatement.setString(4, cinPassportFld.getText());
           
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void setTextField(int id, String username,String email,String numTel,String cinPassport) {

        userId = id;
        usernameFld.setText(username);
        emailFld.setText(email);
        numTelFld.setText(numTel);
        cinPassportFld.setText(cinPassport);
        
        

    }

    void setUpdate(boolean b) {
        this.update = b;

    }

}
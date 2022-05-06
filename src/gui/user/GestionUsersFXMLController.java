/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.user;

import entities.User;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import services.UserService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class GestionUsersFXMLController implements Initializable {
    @FXML
    private Label lListeP;
    @FXML
    private TextField tfUsernameSupp;
    @FXML
    private TextField tfid;
    @FXML
    private TextField tfname;
    @FXML
    private TextField tfeemail;
    @FXML
    private TextField tfusernametoban;
    @FXML
    private TableView<User> userView;
    @FXML
    private TableColumn<User, String> usernamet;
    @FXML
    private TableColumn<User, String> emailt;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AfficherListeUsers(ActionEvent event) {
        UserService us=new UserService();
        try {
            lListeP.setText(us.afficherUser().toString());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }
    
    @FXML
    private void ButtonSuppUser(ActionEvent event) {
        Connection connection = MyDB.getInstance().getConnexion();
        PreparedStatement stmt;

       try {
           stmt=connection.prepareStatement("delete from user where username=?");
           stmt.setString(1,(tfUsernameSupp.getText()));
           int i=stmt.executeUpdate();
           JOptionPane.showMessageDialog(null, "L'utilisateur " + tfUsernameSupp.getText() + "a bien été supprimé");
       } catch (Exception e){
           JOptionPane.showMessageDialog(null, "L'utilisateur n'existe pas");
           e.printStackTrace();
       }
    }

    @FXML
    private void buttonModifierUser(ActionEvent event) {
        try {
            Connection connection = MyDB.getInstance().getConnexion();
            PreparedStatement stmt;
            stmt = connection.prepareStatement("UPDATE user SET email=? WHERE username=?");

            stmt.setString(1, tfeemail.getText());
            stmt.setString(2, tfname.getText());
            int i = stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "L'utilisateur a bien été modifié");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "L'utilisateur n'existe pas");
            e.printStackTrace();
        }
    }
    
    @FXML
    private void buttonBan(ActionEvent event) {
        try {
            Connection connection = MyDB.getInstance().getConnexion();
            PreparedStatement stmt;
            stmt = connection.prepareStatement("UPDATE user SET banned=1 WHERE username=?");
            stmt.setString(1, tfusernametoban.getText());
            int i = stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "L'utilisateur " + tfusernametoban.getText() + " a été banni");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "L'utilisateur n'existe pas");
            e.printStackTrace();
        }
    }
    
    @FXML
    private void buttonDeban(ActionEvent event) {
        try {
            Connection connection = MyDB.getInstance().getConnexion();
            PreparedStatement stmt;
            stmt = connection.prepareStatement("UPDATE user SET banned=0 WHERE username=?");
            stmt.setString(1, tfusernametoban.getText());
            int i = stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "L'utilisateur " + tfusernametoban.getText() + " a été débanni");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "L'utilisateur n'existe pas");
            e.printStackTrace();
        } 
    }
}
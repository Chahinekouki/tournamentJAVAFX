/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.user;

import entities.SessionUser;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import entities.User;
import entities.SessionUser;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import services.UserService;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import utils.MyDB;



/**
 * FXML Controller class
 *
 * @author aymen
 */
public class LoginFXMLController implements Initializable {

    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPassword;
    @FXML
    private TextField tusername;
    
    @FXML
    private Button buttonLogin;
    @FXML
    private Button mdpOublier;
 
    Stage stage;
    Scene scene;
    
    public static int logged;
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    

    public LoginFXMLController() {
    }
    
    PreparedStatement pst = null;
    ResultSet rs = null;

    @FXML
    private void buttonLogin(ActionEvent event) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        String username = "";
        String email = "";
        Connection cnx = MyDB.getInstance().getConnexion();
        String sql = "select * from user where email = ? and password = ?";
        
        try {
            pst = cnx.prepareStatement(sql);
            pst.setString(1, tfEmail.getText());
            pst.setString(2, tfPassword.getText());
            
            rs = pst.executeQuery();
            
            if(rs.next()) {
                if(rs.getInt("banned") == 1) { // On vérifie si l'utilisateur est bannis
                    JOptionPane.showMessageDialog(null, "Votre compte est suspendu");
                } else {
                    logged = rs.getInt("id");
                    try {
                     Parent root  = FXMLLoader.load(getClass().getResource("/gui/user/GestionUsersFXML.fxml"));
                     stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                     scene = new Scene(root);
                     stage.setScene(scene);
                     stage.setTitle("Gérer les utilisateurs");
                     stage.show();
                     } catch(IOException e) {
                           System.out.println(e);
                       }
                    username = rs.getString("username");
                    SessionUser.getInstance().setUsername(username);

                    email = rs.getString("email");
                    SessionUser.getInstance().setEmail(email);
                }
                //  System.out.println("username: " + username);
            } else {
                JOptionPane.showMessageDialog(null, "Les informations de connexion sont incorrects.");
                tfPassword.clear();
            }  
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @FXML
    private void pasEncoreInscrit(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/user/InscriptionFXML.fxml"));
        try {
            Parent root = loader.load();
            Stage mainStage=new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
            mainStage.setTitle("Inscription");
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @FXML
    private void mdpOublier(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/user/FXMLforgotpassword.fxml"));
        try {
            Parent root = loader.load();
            Stage mainStage=new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
            mainStage.setTitle("Réinitialiser votre mot de passe");
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
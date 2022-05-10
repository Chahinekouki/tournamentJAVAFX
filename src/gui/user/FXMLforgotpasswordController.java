/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.user;

import entities.User;
import services.UserService;
import utils.Mailapi;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author mind
 */

public class FXMLforgotpasswordController implements Initializable {

    @FXML
    private TextField tfemail_tel;
    @FXML
    private PasswordField pfnew_password;
    @FXML
    private PasswordField pfconfirm;
    @FXML
    private Button btupdate;
    @FXML
    private Button btsearch;
    @FXML
    private TextField tfverificationcode;
    
    UserService us=new UserService();
    
    int n;
    
    User u =new User();
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btupdate.setVisible(false);
        pfconfirm.setVisible(false);
        pfnew_password.setVisible(false);
        tfverificationcode.setVisible(false);
        Random rand = new Random();
        n = rand.nextInt(99999);
    }    

    @FXML
    private void update(ActionEvent event) {
        System.out.println(u);
        if(tfverificationcode.getText().equals(String.valueOf(n)) && pfconfirm.getText().equals(pfnew_password.getText())){
            u.setPassword(pfnew_password.getText());
            us.modifier(u.getEmail(), u);
            JOptionPane.showMessageDialog(null, "Votre mot de passe a été modifié");
            try {
                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();

                stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("/gui/user/LoginPFXML.fxml"));

                Stage stage =new Stage();

                Scene scene = new Scene(root);

                stage.setTitle("Connexion");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                
            }
        } else if(!tfverificationcode.getText().equals(String.valueOf(n))) {
            JOptionPane.showMessageDialog(null, "Le code entré est incorrect");
        } else if(!pfconfirm.getText().equals(pfnew_password.getText())) {
            JOptionPane.showMessageDialog(null, "Les deux mots de passe ne correspondent pas");
        }
    }

    @FXML
    private void search(ActionEvent event) {    
        if(us.findByEmail(tfemail_tel.getText()).isEmpty()==false){
            JOptionPane.showMessageDialog(null, "Un email avec le code de récupération vous a été envoyé. Patientez ~20 secondes.");
            u=us.findByEmail(tfemail_tel.getText()).get(0);
            Mailapi.send("tournamentlegacyapi@gmail.com", "aymentournament", u.getEmail(), "Mot de passe oublié", "Voici le code pour pouvoir changer votre mot de passe: "+n);
            tfemail_tel.setVisible(false);
            btsearch.setVisible(false);
            btupdate.setVisible(true);
            pfconfirm.setVisible(true);
            pfnew_password.setVisible(true);
            tfverificationcode.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "L'email spécifiée n'existe pas");
        }
    }
    
    @FXML
    private void retourLogin(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/user/LoginPFXML.fxml"));
        try {
            Parent root = loader.load();
            Stage mainStage=new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
            mainStage.setTitle("Connexion");
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();         
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.user;

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
import java.sql.SQLException;
import java.util.regex.Pattern;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import services.UserService;
import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class InscriptionFXMLController implements Initializable {

    @FXML
    private TextField tusername;
    @FXML
    private TextField temail;
    @FXML
    private TextField tmdp;
    @FXML
    private Label lusername;
    @FXML
    private Button btnSinscrire;
    @FXML
    private TextField captchax;
    @FXML
    private TextField captchaEntered;
    
    Stage stage;
    Scene scene;
    
    public void SetUsername(String username) {
        lusername.setText(username);
    }

    
    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnSinscrire(ActionEvent event) {
            if(ValiderTextField(tusername) && ValiderTextField(temail) && ValiderTextField(tmdp)) {
                if(validateEmail(temail.getText())) {
                        User p = new User(tusername.getText(), temail.getText(), tmdp.getText());
                        UserService ps = new UserService();
                        try {
                            ps.ajouterUser(p);
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Succès");
                            alert.setContentText("Vous vous êtes bien inscrit.");
                            alert.showAndWait();
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/user/LoginPFXML.fxml"));
                            try {
                                Parent root = loader.load();
                                Stage mainStage=new Stage();
                                Scene scene = new Scene(root);
                                mainStage.setScene(scene);
                                mainStage.show();
                                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                                stage.close();         
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
                        } catch (SQLException ex) {
                            System.out.println(ex.getMessage());
                        }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Erreur formulaire");
                    alert.setHeaderText(null);
                    alert.setContentText("L'email est incorrect");
                    alert.showAndWait();
                }           
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
    
    private boolean ValiderTextField(TextField T){
        if(T.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur formulaire");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir tous les champs!");
            alert.showAndWait();
            return false;
        }
        if(T.getLength() <4) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur formulaire");
            alert.setHeaderText(null);
            alert.setContentText("Il faut au moins 4 caractères");
            alert.showAndWait();
            return false;
        }
        try{
            int num = Integer.parseInt(T.getText());
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur formulaire");
            alert.setHeaderText(null);
            alert.setContentText("Le champ ne peut pas être un entier");
            alert.showAndWait();
            return false;
          } catch (NumberFormatException e) {

          }
         return true;
    }
    
    public boolean validateEmail(String email){
        return Pattern.matches("[_a-zA-Z1-9]+(\\.[A-Za-z0-9]*)*@[A-Za-z0-9]+\\.[A-Za-z0-9]+(\\.[A-Za-z0-9]*)*", email);
    }
}

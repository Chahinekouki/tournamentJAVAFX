/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.jeu;


import entities.Jeu;
import services.JeuService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Chahine kouki
 */
public class JeuController implements Initializable {

    @FXML
    private ImageView logo;
    @FXML
    private Button BackFromTF;
    @FXML
    private TextField nomjeu;
    @FXML
    private Button CreateJeuFinal;
    
     private boolean update;
     
     int id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
    }    

   

    @FXML
    private void GoBack(ActionEvent event) {
    }

    @FXML
    private void CreateJeuFinal(ActionEvent event) throws SQLException, IOException, ParseException {
       
        String name = nomjeu.getText();
       

        if (name.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
            if (update == true){
            Jeu jeu = new Jeu(id,name);
            JeuService jeuService = new JeuService();
            jeuService.majJeu(jeu);
           
            

        } else if (update==false){
            Jeu jeu = new Jeu(name);
            JeuService jeuService = new JeuService();
            jeuService.ajouterJeu(jeu);
            
        }
            
    }
    }

    void setUpdate(boolean b) {
        this.update = b;

    }
    void setTextField( String name,int id) {

        this.id=id;
        nomjeu.setText(name);

    }
    
}

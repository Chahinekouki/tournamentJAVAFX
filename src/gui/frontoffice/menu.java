package gui.frontoffice;

import gui.frontoffice.*;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Aymen Laroussi
 */
public class menu implements Initializable {

    private AnchorPane pane1;
    private ImageView exit;
    private BorderPane borderpane;
    @FXML
    private JFXButton commandesBtn1;
    @FXML
    private JFXButton commandesBtn11;
    @FXML
    private JFXButton commandesBtn1111;

   
    

  

    

    @Override
    
    public void initialize(URL location, ResourceBundle resources) {
      
        
     
        
   }

    private void categorie(ActionEvent event) {
    }

    @FXML
    private void acceuil (ActionEvent event) {
        loadUI("/gui/frontoffice/home.fxml");
    }


    @FXML
    private void boutique(ActionEvent event) {
        
        loadUI("/boutique/views/market.fxml");
        
    }
    
    private void loadUI(String ui) {
        String ui1="/gui/frontoffice/front.fxml";
    Parent root = null;
    Parent root1 = null;
    try{
        root = FXMLLoader.load(getClass().getResource(ui));
        root1 = FXMLLoader.load(getClass().getResource(ui1));
        
   } catch (IOException ex) {
               Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
           }
    borderpane.setCenter(root);
    borderpane.setTop(root1);
    }
    
  



    @FXML
    private void site(ActionEvent event) {
    }

    @FXML
    private void facebook(ActionEvent event) {
    }


    
    }
   


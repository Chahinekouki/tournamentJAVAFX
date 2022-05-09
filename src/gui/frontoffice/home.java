package gui.frontoffice;

import boutique.Controller.CommentaireController;
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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Aymen Laroussi
 */
public class home implements Initializable {

  
    @FXML
    private WebView web;

   
    private WebEngine engine;

  

    

    @Override
    
    public void initialize(URL location, ResourceBundle resources) {
      
        engine = web.getEngine();
        engine.load("http://127.0.0.1:8000/home");
       
        
   }
    
  

    
  




    
    }
   


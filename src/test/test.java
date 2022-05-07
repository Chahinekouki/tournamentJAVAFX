/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author aymen
 */
public class test extends Application {
    
    public static final String CURRENCY = "$";
//    @Override
//    public void start(Stage primaryStage)throws Exception {
//        
//            Parent parent;
//        try {
//            //parent = FXMLLoader.load(getClass().getResource("/gui/backoffice/Backoffice.fxml"));
//            parent = FXMLLoader.load(getClass().getResource("/boutique/views/market.fxml"));
//            //parent = FXMLLoader.load(getClass().getResource("/sample/sample.fxml"));
//            
//            Scene scene = new Scene(parent);
//            primaryStage.setScene(scene);
//            
//            primaryStage.show();
//            } catch (IOException ex) {
//            Logger.getLogger(testDisplay.class.getName()).log(Level.SEVERE, null, ex);
//        }}
    
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        
       Parent parent;
        try {
           // parent = FXMLLoader.load(getClass().getResource("/gui/backoffice/Backoffice.fxml"));
           //parent = FXMLLoader.load(getClass().getResource("/boutique/views/market.fxml"));
            parent = FXMLLoader.load(getClass().getResource("/gui/user/LoginPFXML.fxml"));
            Scene scene = new Scene(parent);
            primaryStage.setScene(scene);
            
            primaryStage.show();
            } catch (IOException ex) {
            Logger.getLogger(testDisplay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
         
            
         

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

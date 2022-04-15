/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.categories;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author aymen
 */
public class HomePage extends Application {
    double x,y =0;
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../sample/sample.fxml"));
        primaryStage.initStyle((StageStyle.UNDECORATED));
        
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        
        root.setOnMouseDragged(event ->  {
            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setX(event.getScreenY() - y);
                      
        });
        primaryStage.setScene( new Scene(root, 800,500));
        primaryStage.show();
        
     
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

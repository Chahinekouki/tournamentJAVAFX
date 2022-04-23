/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.backoffice;

import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 *
 * @author aymen
 */
public class FxmlLoader {
    private Pane view;
public Pane getPage (String fileName) {
        try {
            URL fileUrl = Main.class.getResource ( "/gui/backoffice/"+fileName + ".fxml");
            System.out.println(fileUrl);
            if(fileUrl == null) {
                throw new java.io.FileNotFoundException ("FXML ELle can't be found");}                                          
            view = new FXMLLoader().load(fileUrl);
         } catch (Exception e) {
                System.out.println("test");}
   return view;
}}

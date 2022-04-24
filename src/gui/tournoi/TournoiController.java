/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.tournoi;

import utils.MyDB;

import entites.Jeu;
import entites.Tournoi;
import services.JeuService;
import services.TournoiService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chahine kouki
 */
public class TournoiController implements Initializable {
    String query = null;
    Connection cnx = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    


    @FXML
    private ImageView logo;
    @FXML
    private Button BackFromTF;
    @FXML
    private TextField nomtournoi;
    @FXML
    private TextField nbrequipe;
    @FXML
    private TextField nbrjoueur;
    @FXML
    private TextField prix;
    @FXML
    private TextField discord;
    @FXML
    private DatePicker time;
    @FXML
    private ComboBox<String> jeuCB;
   JeuService js = new JeuService();
   ObservableList<String> list1 = FXCollections.observableArrayList(js.afficherJeuNom());
    @FXML
    private Button CreateT;
   
    @FXML
    private ComboBox<String> heure;
    @FXML
    private ComboBox<String> minute;
    private boolean update;
     int id;
    
   private void executeQuery(String query) {
       Connection cnx = MyDB.getInstance().getConnexion();
        Statement st;
        try{
            st = cnx.createStatement();
            st.executeUpdate(query);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
      jeuCB.setItems(list1);
      
      heure.getItems().addAll("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");
      
      minute.getItems().addAll("00","15","30","45");
      
      
      
    }    
//private void refresh() {
//        JeuService js = new JeuService();
//         List<Tournoi> tournois = js.afficherJeu();
//        listteams.getItems().clear();
//        listteams.getItems().addAll(equipes);
//    }
    @FXML
    private void GoBack(ActionEvent event) {
        
    }

    @FXML
    private void CreateTF(ActionEvent event) throws IOException, SQLException, ParseException {
        String nom =nomtournoi.getText();     
        int nbreq =Integer.parseInt(nbrequipe.getText());  
        int nbrj =Integer.parseInt(nbrjoueur.getText());  
        String jeu =jeuCB.getValue(); 
        float pr =Float.parseFloat(prix.getText());
        String dc =this.discord.getText();            
        String date =time.getValue().toString();
        String time1=heure.getValue();
        String time2=minute.getValue();
        
         
        
        if ( nom.isEmpty() || String.valueOf(nbrj).isEmpty() || String.valueOf(nbreq).isEmpty()|| jeu.isEmpty() || date.isEmpty() 
                || time1.isEmpty()|| time2.isEmpty()) {
        
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        }else {
            if (update == true){
                
            Tournoi tournoi = new Tournoi(id,nom,nbreq,nbrj,pr,dc,date,jeu);
            TournoiService tournoiService = new TournoiService();
            tournoiService.majTournoi(tournoi);
           
            

        } else if (update == false) {
            Tournoi tournoi = new Tournoi(nom,nbreq,nbrj,pr,dc,date,jeu);
            TournoiService tournoiService = new TournoiService();
            tournoiService.ajoutert(tournoi,time1,time2);
            
        }
        
        }   
        
    }
    @FXML
    private void getDate(ActionEvent event) {
    LocalDate d = time.getValue();
    System.out.println(d.toString());
    }
     void setUpdate(boolean b) {
        this.update = b;

    }
    void setTextField( String name,int id,String x,String discord) {
        this.id=id;
        this.nomtournoi.setText(name);
        this.prix.setText(x);
        this.discord.setText(discord);
        

    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.tournoi;

import entities.Jeu;
import entities.Tournoi;
import entities.SessionUser;
import services.JeuService;
import services.TournoiService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import jxl.write.DateTime;

/**
 * FXML Controller class
 *
 * @author Chahine kouki
 */
public class ShowTournoiController implements Initializable {

    @FXML
    private Button CreateT;
    @FXML
    private Button createTour;
    @FXML
    private TableView<Tournoi> TableTournoi;
    @FXML
    private TableColumn<Tournoi, String> nomTournoi;
    @FXML
    private TableColumn<Tournoi, Integer> nbrEquipe;
    @FXML
    private TableColumn<Tournoi,Integer> nbrJoueur;
    @FXML
    private TableColumn<Tournoi, String> DateT;
    @FXML
    private TableColumn<Tournoi, String> prix;
   
    @FXML
    private ImageView logo;
    
    @FXML
    private Button updateTournoi;
    
 
    @FXML
    private Button DeleteTournoi;

    Tournoi tournoi = null;
    TournoiService ts = new TournoiService();
    @FXML
    private Button inscription;
    Boolean mesTournoi=false;
    @FXML
    private Button mestournoi;
    @FXML
    private Button tournoidisponible;
    
    int idUser;
    Statement stm;
    Connection connexion;
    MediaPlayer mediaplayer;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // TODO
        Media musicFile = new Media ("file:///D:/Assassin.mp3");
       mediaplayer = new MediaPlayer(musicFile);
       mediaplayer.setAutoPlay(true);
       mediaplayer.setVolume(0.1);
       
        nomTournoi.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nbrEquipe.setCellValueFactory(new PropertyValueFactory<>("nbr_equipes"));
        nbrJoueur.setCellValueFactory(new PropertyValueFactory<>("nbr_joueur_eq"));
        DateT.setCellValueFactory(new PropertyValueFactory<>("time"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
//        deleteTournoi.setCellValueFactory(new PropertyValueFactory<>("delete"));
//        updateTournoi.setCellValueFactory(new PropertyValueFactory<>("update"));
        try {
            refresh();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }    
    private void  refresh() throws SQLException {
         TournoiService ts = new TournoiService();
         List<Tournoi> tournois = new ArrayList<>();
         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now().now();
        String dt =dtf.format(now);
        
         if(!mesTournoi){
             for(Tournoi t : ts.affichertournoi()){
                 
                String dtt = (t.getTime().substring(0,19));
                 
                 if(dtt.compareTo(dt)>0){
                     System.out.println(dt);
                   
                     System.out.println("*******"+t.getId());
              tournois.add(t);
                 }
                 
    }
         }
         else{
            
               
             tournois= ts.affichermestournoi(SessionUser.getInstance().getUsername());
             
         }
         
        TableTournoi.getItems().clear();
        TableTournoi.getItems().addAll(tournois);
    
    }

    @FXML
    private void CreateTF(ActionEvent event) throws SQLException {
        refresh();
    }

    @FXML
    private void CreateTournoi(ActionEvent event) throws IOException {
       
         FXMLLoader fxmlLoader =new  FXMLLoader(getClass().getResource("Tournoi.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("ajout");
        stage.setScene(new Scene(root1));
        stage.show();      
    }

    @FXML
    private void updateTournoi(ActionEvent event) {
        
          
         tournoi = TableTournoi.getSelectionModel().getSelectedItem();
         if(mesTournoi){
          if( tournoi !=null) {
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("Tournoi.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(TournoiController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            TournoiController tournoiController = loader.getController();
                            tournoiController.setUpdate(true);
                          
                            System.out.println(tournoi.getNom()+this.tournoi.getId()+
                            String.valueOf(tournoi.getPrix())+tournoi.getDiscord_channel());
                            tournoiController.setTextField(tournoi.getNom(),this.tournoi.getId(),
                            String.valueOf(tournoi.getPrix()),tournoi.getDiscord_channel());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
          }else {
          JOptionPane.showMessageDialog(null, "Veuillez selectionner le tournoi à modifier"); 
          }

        } else {
             JOptionPane.showMessageDialog(null, "Veuillez faire le mise à jours à partir de la liste de vos tournois");   
         }
    }         

        
           
    

    @FXML
    private void DeleteTournoi(ActionEvent event) throws SQLException {
        ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        if(mesTournoi){
  if (TableTournoi.getSelectionModel().getSelectedItem() != null) {
        Dialog<ButtonType> dialog = new Dialog<>();
         dialog.setContentText("Voulez vous supprimé ce tournoi !!!");
        dialog.getDialogPane().getButtonTypes().add(okButtonType);
        dialog.getDialogPane().getButtonTypes().add(cancelButtonType);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
          
                JOptionPane.showMessageDialog(null, "Tournoi Supprimee");
                ts.supprimerTournoi(TableTournoi.getSelectionModel().getSelectedItem().getId());
                List<Tournoi> tournois =  ts.affichertournoi();
         
        TableTournoi.getItems().clear();
        TableTournoi.getItems().addAll(tournois);

            }
            refresh();
  }
       else {
                JOptionPane.showMessageDialog(null, "Veuillez selectionner le jeu à supprimer");    
            }
        }
  {
        JOptionPane.showMessageDialog(null, "Veuillez supprimer à partir de la liste de vos tournoi");    
  }
    
    
}

    @FXML
    private void iscription(ActionEvent event) throws IOException {
//         FXMLLoader fxmlLoader =new  FXMLLoader(getClass().getResource("InscritTournoi.fxml"));
//        Parent root1 = (Parent) fxmlLoader.load();
//        Stage stage = new Stage();
//        stage.setTitle("ajout");
//        
//         
//        stage.setScene(new Scene(root1));
//        stage.show();   
//        
       
        
           tournoi = TableTournoi.getSelectionModel().getSelectedItem();
           if(!mesTournoi){
          if( tournoi !=null) {
                            FXMLLoader loader = new FXMLLoader ();
                             
                            loader.setLocation(getClass().getResource("InscritTournoi.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(TournoiController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            InscritTournoiController inscritTournoiController = loader.getController();
                            System.out.println( TableTournoi.getSelectionModel().getSelectedItem().getId());
                           inscritTournoiController.setTextField( TableTournoi.getSelectionModel().getSelectedItem().getId());
//                            tournoiController.setUpdate(true);
                          
//                            System.out.println(tournoi.getNom()+this.tournoi.getId()+
//                            String.valueOf(tournoi.getPrix())+tournoi.getDiscord_channel());
                          
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
          }else {
          JOptionPane.showMessageDialog(null, "Veuillez selectionner le tournoi à modifier"); 
          }    
           }
           else {
                JOptionPane.showMessageDialog(null, "Veuillez s'inscrire a partir de la liste des tournois disponibles"); 
           }
    }

    @FXML
    private void mestournoi(ActionEvent event) throws SQLException {
        mesTournoi=true;
        refresh();
    }

    @FXML
    private void tournoidisponible(ActionEvent event) throws SQLException {
          mesTournoi=false;
        refresh();
    }
}



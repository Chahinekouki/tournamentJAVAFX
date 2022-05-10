/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.tournoii;

import entities.SessionUser;
import entities.Tournoi;

import gui.tournoii.TournoiController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import services.JeuService;
import services.TournoiService;

/**
 * FXML Controller class
 *
 * @author Chahine kouki
 */
public class TournoisController implements Initializable {

    @FXML
    private TextField searchrest;
    @FXML
    private Button btSearch;
    @FXML
    private VBox chosenTournoiCard;
    @FXML
    private Label TournoiNameLable;
    @FXML
    private ImageView tournoiImg;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Label jeuNameLable1;
    @FXML
    private Label dateNameLable1;
    @FXML
    private Button createTour;
    @FXML
    private Button CreateT;
    @FXML
    private Button DeleteTournoi;
    @FXML
    private Button updateTournoi;
    @FXML
    private Button InscrireTournoi;
    @FXML
    private Button tournoidisponible;
    @FXML
    private Button mestournoi;
     @FXML
    private void searchact2(KeyEvent event) {
    }

    @FXML
    private void btnsearch(ActionEvent event) {
    }

     MediaPlayer mediaplayer;
    Boolean mesTournoi=false;
 private Listener listener;
 List<Tournoi> tournois = new ArrayList<>();   
  TournoiService ts = new TournoiService();
    JeuService js= new JeuService();
  Tournoi tournoi;
      
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Media musicFile = new Media ("file:///D:/Assassin.mp3");
       mediaplayer = new MediaPlayer(musicFile);
       mediaplayer.setAutoPlay(true);
       mediaplayer.setVolume(0.1);
            tournois.clear();
        try {
            refresh();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(TournoisController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

 public void refresh () throws SQLException {
     grid.getChildren().clear();
     if(!mesTournoi){
         tournois.clear(); 
         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
         LocalDateTime now = LocalDateTime.now().now();
        String dt =dtf.format(now);
         for(Tournoi t : ts.affichertournoi()){
                 
                String dtt = (t.getTime().substring(0,19));
                 
                 if(dtt.compareTo(dt)>0){
                     System.out.println(dt);
                   
                     System.out.println("*******"+t.getId());
                     
              tournois.add(t);
                     System.out.println("tournois"+tournois);
                 }
                 
    }
     if (tournois.size() > 0) {
          setChosenTournoi(tournois.get(0));
          listener = new Listener() {
              public Tournoi tournoi;
              @Override
              public void OnClickListener(Tournoi tournoi) {
                  System.out.println("Nom"+tournoi.getNom());
                  try {
                      setChosenTournoi(tournoi);
                  } catch (SQLException ex) {
                      Logger.getLogger(TournoisController.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  
              }
              
          };
          
          
          
      }
      
      
      int column = 0;
      int row = 1;
      try {
          System.out.println("solution finale "+tournois+"********************");
          for (int i = 0; i < tournois.size(); i++) {
              FXMLLoader fxmlLoader = new FXMLLoader();
              fxmlLoader.setLocation(getClass().getResource("/gui/tournoii/tour.fxml"));
              AnchorPane anchorPane = fxmlLoader.load();
              TourController tourController = fxmlLoader.getController();
              tourController.setData(tournois.get(i),listener);
              
              if (column == 3) {
                  column = 0;
                  row++;
              }
              
              grid.add(anchorPane, column++, row); //(child,column,row)
              //set grid width
              grid.setMinWidth(Region.USE_COMPUTED_SIZE);
              grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
              grid.setMaxWidth(Region.USE_PREF_SIZE);
              
              //set grid height
              grid.setMinHeight(Region.USE_COMPUTED_SIZE);
              grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
              grid.setMaxHeight(Region.USE_PREF_SIZE);
              
              GridPane.setMargin(anchorPane, new Insets(23));
          }
      } catch (IOException e) {
          e.printStackTrace();
      }   
   
     }
     
     if(mesTournoi){
          tournois.clear();
         
         tournois=ts.affichermestournoi();
         System.out.println("tournois======"+tournois);
         if (tournois.size() > 0) {
          setChosenTournoi(tournois.get(0));
          listener = new Listener() {
              public Tournoi tournoi;
              @Override
              public void OnClickListener(Tournoi tournoi) {
                  System.out.println("Nom"+tournoi.getNom());
                  try {
                      setChosenTournoi(tournoi);
                  } catch (SQLException ex) {
                      Logger.getLogger(TournoisController.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  
              }
              
          };
          
          
          
      }
      
      
      int column = 0;
      int row = 1;
      try {
          System.out.println("solution finale "+tournois+"********************");
          for (int i = 0; i < tournois.size(); i++) {
              FXMLLoader fxmlLoader = new FXMLLoader();
              fxmlLoader.setLocation(getClass().getResource("/gui/tournoii/tour.fxml"));
              AnchorPane anchorPane = fxmlLoader.load();
              TourController tourController = fxmlLoader.getController();
              tourController.setData(tournois.get(i),listener);
              
              if (column == 3) {
                  column = 0;
                  row++;
              }
              
              grid.add(anchorPane, column++, row); //(child,column,row)
              //set grid width
              grid.setMinWidth(Region.USE_COMPUTED_SIZE);
              grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
              grid.setMaxWidth(Region.USE_PREF_SIZE);
              
              //set grid height
              grid.setMinHeight(Region.USE_COMPUTED_SIZE);
              grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
              grid.setMaxHeight(Region.USE_PREF_SIZE);
              
              GridPane.setMargin(anchorPane, new Insets(23));
              
          }
      } catch (IOException e) {
          e.printStackTrace();
      }
     }
 
     
      
// TODO

 }
 
  private void setChosenTournoi(Tournoi tournoi) throws SQLException {
      this.tournoi=tournoi;
      tournoi.setJeu(js.getJeuParId(tournoi.getId()));
        TournoiNameLable.setText(tournoi.getNom());
        jeuNameLable1.setText(tournoi.getJeu());
        
        dateNameLable1.setText(tournoi.getTime().substring(0, 10));
    
    }

    @FXML
    private void CreateTournoi(ActionEvent event) throws IOException, SQLException {
            FXMLLoader fxmlLoader =new  FXMLLoader(getClass().getResource("Tournoi.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("ajout");
        stage.setScene(new Scene(root1));
        stage.show(); 
        refresh();
        
    }

    @FXML
    private void CreateTF(ActionEvent event) throws SQLException {
          refresh();
    }


  @FXML
    private void DeleteTournoi(ActionEvent event) throws SQLException, IOException {
          ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
       ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        if(mesTournoi){
  if (tournoi.getNom() != null) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setContentText("Voulez vous supprimé ce tournoi !!!");
        dialog.getDialogPane().getButtonTypes().add(okButtonType);        dialog.getDialogPane().getButtonTypes().add(cancelButtonType);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
          
              
//                System.out.println(tournoi.getId());
                ts.supprimerTournoi(tournoi.getId());
                  JOptionPane.showMessageDialog(null, "Tournoi Supprimee");
                  refresh();
                        
  }
//               Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
//                stage.close();
          
       
                    
  
//          FXMLLoader fxmlLoader =new  FXMLLoader(getClass().getResource("/gui/tournoii/tournois.fxml"));
//         Parent root1 = (Parent) fxmlLoader.load();
//        Stage stage = new Stage();
//        stage.setTitle("ajout");
//        stage.setScene(new Scene(root1));
//        stage.show(); 
//        refresh();
  }
  
       else {
                JOptionPane.showMessageDialog(null, "Veuillez selectionner le jeu à supprimer");    
            }
        }
        else
  {
        JOptionPane.showMessageDialog(null, "Veuillez supprimer à partir de la liste de vos tournoi");    
  }
    }
        
    

    @FXML
  private void updateTournoi(ActionEvent event) {
          
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
    private void InscrireTournoi(ActionEvent event) {
         
           if(!mesTournoi){
          if( tournoi !=null) {
                            FXMLLoader loader = new FXMLLoader ();
                             
                            loader.setLocation(getClass().getResource("InscritTournoi.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(TournoiController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                       InscritTournoiController   inscritTournoiController = loader.getController();
                            
                           inscritTournoiController.setTextField(tournoi.getId());
//                            tournoiController.setUpdate(true);
                          
//                            System.out.println(tournoi.getNom()+this.tournoi.getId()+
//                            String.valueOf(tournoi.getPrix())+tournoi.getDiscord_channel());
                          
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
          }else {
          JOptionPane.showMessageDialog(null, "Veuillez selectionner le tournoi à s'inscrire"); 
          }    
           }
           else {
                JOptionPane.showMessageDialog(null, "Veuillez s'inscrire a partir de la liste des tournois disponibles"); 
           }
    }

    @FXML
    private void tournoidisponible(ActionEvent event) throws SQLException {
        mesTournoi=false;
        refresh();
    }

    @FXML
    private void mestournoi(ActionEvent event) throws SQLException {
          mesTournoi=true;
        refresh();
    }

   
  
  
  
}

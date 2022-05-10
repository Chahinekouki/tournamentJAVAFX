/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.tournoii;

import entities.Equipe;
import entities.SessionUser;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import services.TournoiService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Chahine kouki
 */
public class InscritTournoiController implements Initializable {

    @FXML
    private ImageView logo1;
    @FXML
    private AnchorPane joueurs;
    @FXML
    private TableView<Equipe> TableJoueurs;
    @FXML
    private TableColumn<Equipe, String> nomJoueurs;
    int id ;
    @FXML
    private Button refreshJ;
    @FXML
    private TableColumn<Equipe, String> idequipe;
    @FXML
    private TableColumn<Equipe, String> labelEquipe;
    @FXML
    private Button updateTournoi;
     TournoiService ts = new TournoiService();
    @FXML
    private Button AnnulerInscription;
    
    String s="";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              
                        labelEquipe.setCellValueFactory(new PropertyValueFactory<>("label"));
        nomJoueurs.setCellValueFactory(new PropertyValueFactory<>("joueurs"));
        
        try {
            refresh();
        } catch (SQLException ex) {
            Logger.getLogger(InscritTournoiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
    }
     private void  refresh() throws SQLException {
         TournoiService ts = new TournoiService();
         List<Equipe> jrs = ts.afficherJoueurs(id);
        TableJoueurs.getItems().clear();
        TableJoueurs.getItems().addAll(jrs);
        
    }
   void setTextField( int id) {

        this.id=id;
       

    }

    @FXML
    private void refreshJ(ActionEvent event) throws SQLException {
        refresh();
    }

    @FXML
    private void inscrireEquipe(ActionEvent event) throws SQLException {
         ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
  if (TableJoueurs.getSelectionModel().getSelectedItem() != null) {
       List<Equipe> jrs = ts.afficherJoueurs(id);
      for(Equipe e:jrs){
            e.getJoueurs();
            s = s.concat(e.getJoueurs());
      }
            
        
       if ( s.contains(SessionUser.getInstance().getUsername()) ){
           JOptionPane.showMessageDialog(null, "vous etes déja inscrit !! ");
       } else {
           Dialog<ButtonType> dialog = new Dialog<>();
           dialog.setContentText("Voulez vous rejoindre cette equipe  !!!");
           dialog.getDialogPane().getButtonTypes().add(okButtonType);
           dialog.getDialogPane().getButtonTypes().add(cancelButtonType);
           
           Optional<ButtonType> result = dialog.showAndWait();
           if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
               ts.inscrireJoueur(TableJoueurs.getSelectionModel().getSelectedItem());
               s="";
               refresh();
              String tilte = SessionUser.getInstance().getUsername()+" inscrit avec succes";
              // notification bundle 
            String message = SessionUser.getInstance().getUsername();
               TrayNotification tray = new TrayNotification();
               AnimationType type = AnimationType.POPUP;
        
            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
               
               // sms bundle 
              ts.envoyerSMS(id);
               
           }                      
       

            }
           
  
  } else {
                JOptionPane.showMessageDialog(null, "Veuillez selectionner le tournoi à s'inscrire");    
            }
    }

    @FXML
    private void AnnulerInscription(ActionEvent event) throws SQLException {
   
    
      ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
  if (TableJoueurs.getSelectionModel().getSelectedItem() != null) {
       List<Equipe> jrs = ts.afficherJoueurs(id);
      for(Equipe e:jrs){
            e.getJoueurs();
            s = s.concat(e.getJoueurs());
      }
            
        
       if ( s.contains(SessionUser.getInstance().getUsername()) ){
           Dialog<ButtonType> dialog = new Dialog<>();
           dialog.setContentText("Voulez vous se desinscrire de cette equipe  !!!");
           dialog.getDialogPane().getButtonTypes().add(okButtonType);
           dialog.getDialogPane().getButtonTypes().add(cancelButtonType);
           
           Optional<ButtonType> result = dialog.showAndWait();
           if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
               ts.desinscrireJoueur(TableJoueurs.getSelectionModel().getSelectedItem());
               s="";
               refresh();
               JOptionPane.showMessageDialog(null, "Joueur desinscrit");
               
          // envoyersms     
               
               
           }                      
       

            }else {
           
          JOptionPane.showMessageDialog(null, "vous etes n'etes pas inscrit !! "); 
}
       
           
  } else {
                JOptionPane.showMessageDialog(null, "Veuillez selectionner le tournoi à se désinscrire");    
            }

}
    
}

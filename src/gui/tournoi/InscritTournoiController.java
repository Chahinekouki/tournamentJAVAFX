/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.tournoi;

import entites.Equipe;
import entites.Jeu;
import entites.Tournoi;
import services.JeuService;
import services.TournoiService;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

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
        Dialog<ButtonType> dialog = new Dialog<>();
         dialog.setContentText("Voulez vous rejoindre cette equipe  !!!");
        dialog.getDialogPane().getButtonTypes().add(okButtonType);
        dialog.getDialogPane().getButtonTypes().add(cancelButtonType);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
          
              
      
               ts.inscrireJoueur(TableJoueurs.getSelectionModel().getSelectedItem());
         
//        TableTournoi.getItems().clear();
//        TableTournoi.getItems().addAll(tournois);
             JOptionPane.showMessageDialog(null, "succes");
              refresh();
            }
           
  }
       else {
                JOptionPane.showMessageDialog(null, "Veuillez selectionner le tournoi à s'inscrire");    
            }
    }

    @FXML
    private void AnnulerInscription(ActionEvent event) throws SQLException {
          ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
  if (TableJoueurs.getSelectionModel().getSelectedItem() != null) {
        Dialog<ButtonType> dialog = new Dialog<>();
         dialog.setContentText("Voulez vous se desinscrire de cette equipe  !!!");
        dialog.getDialogPane().getButtonTypes().add(okButtonType);
        dialog.getDialogPane().getButtonTypes().add(cancelButtonType);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
          
              
      
               ts.desinscrireJoueur(TableJoueurs.getSelectionModel().getSelectedItem());
         
//        TableTournoi.getItems().clear();
//        TableTournoi.getItems().addAll(tournois);
             JOptionPane.showMessageDialog(null, "succes");
             refresh();
            }
            
  }
       else {
                JOptionPane.showMessageDialog(null, "Veuillez selectionner le jeu à supprimer");    
            }
    }

}
      
     
    


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.tournoii;

import entities.Tournoi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Chahine kouki
 */
public class TourController  {

    @FXML
    private Label nomTournoi;
    @FXML
    private Label nombreequipe;
    @FXML
    private ImageView imagetournoi;
    
  private Tournoi tournoi;
   private Listener listener;
    
    public void setData(Tournoi tournoi,Listener listener) {
       this.tournoi = tournoi;
       this.listener=listener;
        nomTournoi.setText(tournoi.getNom());
        Image image = new Image(getClass().getResourceAsStream("/resources/274699900_259835589652111_5841210095208234283_n.png"));
       imagetournoi.setImage(image);
       nombreequipe.setText("Equipe : "+String.valueOf(tournoi.getNbr_equipes()));

    }

    @FXML
    private void click(MouseEvent event) {
        listener.OnClickListener(tournoi);
    }
   
}

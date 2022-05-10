package boutique.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import boutique.main.MyListener;
import entities.Commentaire;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import utils.MyDB;

/**
 *
 * @author Aymen Laroussi
 */
public class CommentaireController implements Initializable {

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private List<Commentaire> commentaires = new ArrayList<>();
    private Image image;
    private MyListener myListener;
    Connection connexion;
    Statement stm;
    float total;
    String total1;
    String produitid;
    String randomHex;
    String rate;
    String produit;
    
    
    public CommentaireController() {
        connexion = MyDB.getInstance().getConnexion();
    }

     public List<Commentaire> afficheProduit() throws SQLException {
        List<Commentaire> commentaires = new ArrayList<>();
         System.out.println(produit);
        String req = "select distinct r.rating,r.entity_code,c.user_id,c.message,c.date,p.id,u.username,u.id,r.entity_code from commentaires c,rating r,user u ,produits p where r.entity_code = p.id and c.produit_id = p.id and p.id ="+produit+" ORDER BY c.date desc";
        stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Commentaire c = new Commentaire(rst.getInt("id"),
                    rst.getString("username"),
                    rst.getString("message"),
                    rst.getDate("date"),
                    rst.getFloat("rating"));
            commentaires.add(c);
             System.out.println(commentaires);
        }
        return commentaires;
        
    }
     
     
     
     
  public void Avis(String id ){
       produit=id;
       try {
            commentaires.addAll(afficheProduit());
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < commentaires.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/boutique/views/itemCommentaire.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemCommentaire ItemCommentaire = fxmlLoader.getController();
                ItemCommentaire.setData(commentaires.get(i),myListener);

                if (column == 1) {
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

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
      
    }
  
    
   

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
        
       
    }


    private void Commenter(MouseEvent event) {
        
    }

    private void Commenter1(MouseDragEvent event) {
        
    }


}

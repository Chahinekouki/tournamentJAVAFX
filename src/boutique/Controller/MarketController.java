package boutique.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import boutique.main.Main;
import boutique.main.MyListener;
import entities.Produit;
import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import utils.MyDB;

/**
 *
 * @author Aymen Laroussi
 */
public class MarketController implements Initializable {
    @FXML
    private VBox chosenFruitCard;

    @FXML
    private Label fruitNameLable;

    @FXML
    private ImageView fruitImg;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private List<Produit> produits = new ArrayList<>();
    private Image image;
    private MyListener myListener;
    Connection connexion;
    Statement stm;
    @FXML
    private Label descriptionLable;
    @FXML
    private Label fruitPromoLabel;
    float total;
    String total1;
    @FXML
    private Label produitIdLabel;
    @FXML
    private Label fruitPriceLabel;
    @FXML
    private Rating rating;
    String produitid;
    String randomHex;
    String rate;
    @FXML
    private Label ProduitRefLable1;
    
    
    public MarketController() {
        connexion = MyDB.getInstance().getConnexion();
    }

     public List<Produit> afficheProduit() throws SQLException {
        List<Produit> produits = new ArrayList<>();
        String req = "select * from produits";
        stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Produit p = new Produit(rst.getInt("id"),//or rst.getInt(1)
                    rst.getInt("categories_id"),
                    rst.getString("titre"),
                    rst.getString("description"),
                    rst.getFloat("promo"),
                    rst.getFloat("stock"),
                    rst.getBoolean("flash"),
                    rst.getString("image"),
                    rst.getString("ref"),
                    rst.getString("longdescription"),
                    rst.getFloat("prix"));
            produits.add(p);
        }
        return produits;
    }
     
     
     
     
    public void afficheRating(int p) throws SQLException {
        
       try{ System.out.println(produitid);
           System.out.println(p);
        String req = "SELECT AVG(rating) FROM `rating` where entity_code like "+p+"";
        stm = connexion.createStatement();
        ResultSet rst1 = stm.executeQuery(req);
        if(rst1.next()){
         double add1 = rst1.getDouble(1);
           System.out.println(add1);
        rating.setRating((int)add1);
        }
       }catch (SQLException err) {
        System.out.println(err.getMessage());
    }
        
        
                    
        
    }
    
  
    static String getRandomString(){
        int r = (int) (Math.random()*5);
        String color = new String [] {"FB8CAB","E65C9C","CF268A","AF1281","6B0772","360167"}[r];
        return color;
    }
    private void setChosenFruit(Produit produit) {
        
        
            produitid=(Integer.toString(produit.getId()));
            try {
            afficheRating(produit.getId());
            } catch (SQLException ex) {
            Logger.getLogger(MarketController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            
            
            
            
            
            
            fruitNameLable.setText(produit.getTitre());
            ProduitRefLable1.setText("Réf : #"+produit.getRef());
            float promo=produit.getPromo() ;
            if (promo != 0){
                total = (produit.getPromo()*produit.getPrix())/100;
                if(total == (long) total)
                    total1=String.format("%d",(long)total);
                else
                    total1 =String.format("%s",total);
            }
            
            else{
                total=produit.getPrix();
                if(total == (long) total)
                    total1=String.format("%d",(long)total);
                else
                    total1 =String.format("%s",total);
                
            }
            fruitPriceLabel.setText( total1+" TND");
            fruitPromoLabel.setText(produit.getPrix()+"TND");
            System.out.println(total+" TND");
            descriptionLable.setText(produit.getDescription());
            
            String A = "C:\\Pi\\public\\uploads\\"+produit.getImage();
            File F1 = new File(A);
            Image image = new Image(F1.toURI().toString());
            
            randomHex = getRandomString();
            fruitImg.setImage(image);
            chosenFruitCard.setStyle("-fx-background-color: #" + randomHex + ";\n" +
                    "    -fx-background-radius: 30;");
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            produits.addAll(afficheProduit());
        } catch (SQLException ex) {
            Logger.getLogger(MarketController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (produits.size() > 0) {
            setChosenFruit(produits.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Produit produit) {
                    setChosenFruit(produit);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < produits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/boutique/views/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(produits.get(i),myListener);

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

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ajoutPanier(MouseEvent event) {
        //FARES
    }

    @FXML
    private void Commenter(MouseEvent event) {
        
       refresh();
    }

    private void Commenter1(MouseDragEvent event) {
        
    }

    @FXML
    private void Commenter1(MouseEvent event) {
        
        
        
        
        
        
        
        
        
     
    }

    
    private void refresh() {
        
               try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/boutique/views/addCommentaire.fxml"));
            Parent root = (Parent) loader.load();

            AddCommentaireController secController=loader.getController();
            
            secController.Rating(rating.getRating(),produitid,randomHex);
            System.out.println(rating.getRating());

            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    @FXML
    private void avisList(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/boutique/views/marketCommentaire.fxml"));
            Parent root = (Parent) loader.load();

            CommentaireController secController=loader.getController();
            
            secController.Avis(produitid); 

            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    

}

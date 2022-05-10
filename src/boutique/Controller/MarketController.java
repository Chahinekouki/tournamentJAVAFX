package boutique.Controller;

import javafx.fxml.FXML;
import animatefx.animation.*;
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
import boutique.main.MyListener1;
import entities.Categories;
import entities.Produit;
import entities.SessionUser;
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
import javafx.scene.control.TextField;
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
    
    private List<Categories> categories = new ArrayList<>();
    private List<Produit> produits = new ArrayList<>();
    private Image image;
    private MyListener myListener;
    private MyListener1 myListener1;
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
    @FXML
    private TextField rech;
    @FXML
    private ScrollPane scroll1;
    @FXML
    private GridPane grid1;
    String cat;
    @FXML
    private Label user;
    @FXML
    private ImageView pan;
    
    
    public MarketController() {
        connexion = MyDB.getInstance().getConnexion();
    }

    
    public List<Produit> triCategorie(int id) throws SQLException {
          
        List<Produit> produits = new ArrayList<>();
        String req = "select * from produits where categories_id like '%"+id+"%'";
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
    
      public List<Produit> afficheRecherche() throws SQLException {
          
        List<Produit> produits = new ArrayList<>();
        String req = "select * from produits where titre like '%"+rech.getText()+"%' or ref like '%"+rech.getText()+"%'";
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
     
     
     public List<Categories> afficheCategorie() throws SQLException {
        List<Categories> categories = new ArrayList<>();
        String req = "select * from categories";
        stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Categories c = new Categories(rst.getInt("id"),//or rst.getInt(1)
                    rst.getString("nom"));
            categories.add(c);
        }
        return categories;
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
    }}
  
    static String getRandomString(){
        int r = (int) (Math.random()*5);
        String color = new String [] {"FB8CAB","E65C9C","CF268A","AF1281","6B0772","360167"}[r];
        return color;
    }
    private void setChosenFruit(Produit produit) {
        
        
            produitid=(Integer.toString(produit.getId()));
            System.out.println("chaine"+produitid);
            try {
            afficheRating(produit.getId());
            } catch (SQLException ex) {
            Logger.getLogger(MarketController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            fruitNameLable.setText(produit.getTitre());
            ProduitRefLable1.setText("Réf : #"+produit.getRef());
            float promo=produit.getPromo() ;
            if (promo != 0){
                
                total = produit.getPrix()-((produit.getPromo()*produit.getPrix())/100);
                
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
            fruitPromoLabel.setText(produit.getPrix()+" TND");
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
          if (SessionUser.getInstance().getUsername()!=null){
              user.setText("Salut!, "+SessionUser.getInstance().getUsername());
              pan.setVisible(true);
              user.setVisible(true);
          }
          else{
             user.setVisible(true);
             pan.setVisible(false);
             
             
        }
        try {
            produits.addAll(afficheProduit());
            categories.addAll(afficheCategorie());
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
        
        
         if (categories.size() > 0) {
            
            myListener1 = new MyListener1() {
                @Override
                public void onClickListener(Categories categorie) {
                   System.out.println("test");
                        Recherchecat(categorie.getId());
                        
                    
                }
            };
        }
         column = 0;
         row = 1;
        try {
            for (int i = 0; i < categories.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/boutique/views/cat.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData1(categories.get(i),myListener1);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid1.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid1.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid1.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid1.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid1.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid1.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid1.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    @FXML
      public void Recherche() {
        
        try {
            produits.removeAll(produits);
            produits.addAll(afficheRecherche());
            if (produits == null){
               produits.addAll(afficheProduit());
            }
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
            grid.getChildren().clear();}
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
      
     public void Recherchecat(int id) {
        
        try {
            produits.removeAll(produits);
            produits.addAll(triCategorie(id));
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
            grid.getChildren().clear();}
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
        if (SessionUser.getInstance().getUsername()==null){
              try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/gui/user/LoginPFXML.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }}
              else{
        
               try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/boutique/views/addCommentaire.fxml"));
            Parent root = (Parent) loader.load();

            AddCommentaireController secController=loader.getController();
                   System.out.println(produitid);
            secController.Rating(rating.getRating(),produitid,randomHex);
            System.out.println(rating.getRating());

            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }}
        
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

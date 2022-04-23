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
import javafx.scene.input.MouseEvent;
import utils.MyDB;

public class MarketController implements Initializable {
    @FXML
    private VBox chosenFruitCard;

    @FXML
    private Label fruitNameLable;

    @FXML
    private Label fruitPriceLabel;

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
    
  
    static String getRandomString(){
        int r = (int) (Math.random()*5);
        String color = new String [] {"FB8CAB","E65C9C","CF268A","AF1281","6B0772","360167"}[r];
        return color;
    }
    private void setChosenFruit(Produit produit) {
        fruitNameLable.setText(produit.getTitre());
        float promo=produit.getPromo() ;
        if (promo == 0){
            total = (produit.getPromo()/produit.getPrix()*100);
            
            
        }
        fruitPriceLabel.setText( total+" TND");
        fruitPromoLabel.setText(produit.getPrix()+" TND");
        descriptionLable.setText(produit.getDescription());
           
                             String A = "C:\\Pi\\public\\uploads\\"+produit.getImage();
                                          File F1 = new File(A);
                                           Image image = new Image(F1.toURI().toString());
                                           

        fruitImg.setImage(image);
        chosenFruitCard.setStyle("-fx-background-color: #" + getRandomString() + ";\n" +
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

}

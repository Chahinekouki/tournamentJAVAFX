package evenement.Controller;

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
import evenement.main.MyListener;
import entities.Evenement;
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
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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
    
public static int iddata;
    private List<Evenement> events = new ArrayList<>();
    private Image image;
    private MyListener myListener;
    Connection connexion;
    Statement stm;
    float total;
    String total1;
    @FXML
    private Label produitIdLabel;
    String produitid;
    int produitidd;
    String randomHex;
    String rate;
    @FXML
    private Label ProduitRefLable1;
    @FXML
    private TextField rech;
    
    float qte;
    private Label user;
    @FXML
    private Button details;
    
       /* void searchact2(KeyEvent event) {
        System.out.println(recherche.getText().toString());
          grid.getChildren().clear();
        events.clear();
        marketshow();
    }*/
    public MarketController() {
        connexion = MyDB.getInstance().getConnexion();
    }

    
      public List<Evenement> afficheRecherche() throws SQLException {
          
        List<Evenement> events = new ArrayList<>();
        String req = "select * from evenement ";
        stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Evenement p = new Evenement(rst.getInt("id"),
                    rst.getString("nomeven"),
                    rst.getString("lieuevent"),
                    rst.getString("datevent"),
                    rst.getString("heurevent"),
                    rst.getString("datefin"),
                    rst.getInt("nbrplace"),
                    rst.getString("image"),
                    rst.getString("description"));
                        if (rech.getText().isEmpty() )
                                  events.add(p);
                                else if (rst.getString("nomeven").toString().toUpperCase().indexOf(rech.getText().toUpperCase())>-1)
                                {
                                     events.add(p);
                                }
                
          
            
              
        }
        return events;
    }
     
     
     public List<Evenement> afficheProduit() throws SQLException {
        List<Evenement> events = new ArrayList<>();
        String req = "select * from evenement";
        stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Evenement p = new Evenement(rst.getInt("id"),
                    rst.getString("nomeven"),
                    rst.getString("lieuevent"),
                    rst.getString("datevent"),
                    rst.getString("heurevent"),
                    rst.getString("datefin"),
                    rst.getInt("nbrplace"),
                    rst.getString("image"),
                    rst.getString("description"));
            events.add(p);
        }
        return events;
    }
     
     
     
    
    private void setChosenFruit(Evenement evenement) {
        
        MarketController.iddata=evenement.getId();
        System.out.println(evenement.getId());
            produitid=(Integer.toString(evenement.getId()));
            qte=(evenement.getNombre());
             produitidd=Integer.valueOf(produitid);
         
            fruitNameLable.setText(evenement.getNom());
            ProduitRefLable1.setText(evenement.getDatefin());
           
            
            
            String A = "C:\\Pi\\public\\uploads\\"+evenement.getImagee();
            File F1 = new File(A);
            Image image = new Image(F1.toURI().toString());
            
           
            fruitImg.setImage(image);
            
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        try {
            events.addAll(afficheProduit());
        } catch (SQLException ex) {
            Logger.getLogger(MarketController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (events.size() > 0) {
            setChosenFruit(events.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Evenement evenement) {
                    setChosenFruit(evenement);
                }
               
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < events.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/evenement/views/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(events.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); 
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
      public void Recherche() {
        
        try {
            
            events.removeAll(events);
            events.addAll(afficheRecherche());
            System.out.println(events);
        } catch (SQLException ex) {
            Logger.getLogger(MarketController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (events.size() > 0) {
            setChosenFruit(events.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Evenement evenement) {
                    setChosenFruit(evenement);
                }
            };
        }
       
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < events.size(); i++) {
            grid.getChildren().clear();}
            for (int i = 0; i < events.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/evenement/views/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(events.get(i),myListener);

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
    private void details(ActionEvent event)  
    {
        try 
        {
           
            Parent parent =  FXMLLoader.load(getClass().getResource("/evenement/views/event.fxml"));
            details.getScene().setRoot(parent);
        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
       @FXML
    void inputrech(KeyEvent event) {
        grid.getChildren().clear();
    Recherche();
    

    }

    }
    

    

    

  


    
    
    



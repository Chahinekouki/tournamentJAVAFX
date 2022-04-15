package gui.backoffice;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class Controller implements Initializable {

    @FXML
    private AnchorPane pane1;
    @FXML
    private ImageView exit;
    @FXML
    private ImageView menu;
    @FXML
    private AnchorPane pane2;

   
    

  

    

    boolean menuAnim =true ;
    private JFXButton produitbtn;
    @FXML
    private ImageView utilisateursIcon;
    @FXML
    private ImageView jeuxIcon;
    @FXML
    private ImageView evenementsIcon;
    @FXML
    private ImageView sponsorsIcon;
    @FXML
    private ImageView categoriesIcon;
    @FXML
    private ImageView produitsIcon;
    @FXML
    private ImageView avisIcon;
    @FXML
    private ImageView commandesIcon;
    @FXML
    private JFXButton utilisateursBtn;
    @FXML
    private JFXButton jeuxBtn;
    @FXML
    private JFXButton evenementsBtn;
    @FXML
    private JFXButton sponsorsBtn;
    @FXML
    private JFXButton categoriesBtn;
    @FXML
    private JFXButton proditsBtn;
    @FXML
    private JFXButton avisBtn;
    @FXML
    private JFXButton commandesBtn;
    @FXML
    private AnchorPane utilisateur;
    @FXML
    private ImageView exit11;
    @FXML
    private AnchorPane jeux;
    @FXML
    private ImageView exit1;
    @FXML
    private AnchorPane evenements;
    @FXML
    private AnchorPane sponsors;
    @FXML
    private ImageView exit111;
    @FXML
    private AnchorPane categories;
    @FXML
    private ImageView exit112;
    @FXML
    private AnchorPane produits;
    @FXML
    private ImageView exit113;
    @FXML
    private AnchorPane avis;
    @FXML
    private ImageView exit1111;
    @FXML
    private AnchorPane commandes;
    @FXML
    private ImageView exit1121;

    
     public void callback(){
        menuAnim=Boolean.TRUE;
                    FadeTransition fadeTransition1=new FadeTransition(Duration.seconds(0.5),pane1);
            fadeTransition1.setFromValue(0.15);
            fadeTransition1.setToValue(0);
            fadeTransition1.play();

            fadeTransition1.setOnFinished(event1 -> {
                pane1.setVisible(false);
            });


            TranslateTransition translateTransition1=new TranslateTransition(Duration.seconds(0.5),pane2);
            translateTransition1.setByX(-600);
            translateTransition1.play();
          
             
     }
    @Override
    
    public void initialize(URL location, ResourceBundle resources) {

       exit.setOnMouseClicked(event -> {
            System.exit(0);
        });

        pane1.setVisible(false);
        produits.setVisible(false);
        FadeTransition fadeTransition=new FadeTransition(Duration.seconds(0.5),pane1);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        TranslateTransition translateTransition=new TranslateTransition(Duration.seconds(0.5),pane2);
        translateTransition.setByX(-600);
        translateTransition.play();
        
        menu.setOnMouseClicked(event -> {
             if ( menuAnim){
                 menuAnim =Boolean.FALSE;
              
            pane1.setVisible(true);

            FadeTransition fadeTransition1=new FadeTransition(Duration.seconds(0.5),pane1);
            fadeTransition1.setFromValue(0);
            fadeTransition1.setToValue(0.15);
            fadeTransition1.play();

            TranslateTransition translateTransition1=new TranslateTransition(Duration.seconds(0.5),pane2);
            translateTransition1.setByX(+600);
            translateTransition1.play();
            
             }
             else {
                 menuAnim=Boolean.TRUE;
                    FadeTransition fadeTransition1=new FadeTransition(Duration.seconds(0.5),pane1);
            fadeTransition1.setFromValue(0.15);
            fadeTransition1.setToValue(0);
            fadeTransition1.play();

            fadeTransition1.setOnFinished(event1 -> {
                pane1.setVisible(false);
            });


            TranslateTransition translateTransition1=new TranslateTransition(Duration.seconds(0.5),pane2);
            translateTransition1.setByX(-600);
            translateTransition1.play();
          
             }
        });
        
        
        produits.setVisible(false);
                  jeux.setVisible(false);
                  evenements.setVisible(false);
                  sponsors.setVisible(false);
                  categories.setVisible(false);
                  produits.setVisible(false);
                  commandes.setVisible(false);
                  utilisateur.setVisible(false);
                  avis.setVisible(false);
            proditsBtn.setOnMouseClicked(event -> {             
                  produits.setVisible(true);
                  jeux.setVisible(false);
                  evenements.setVisible(false);
                  sponsors.setVisible(false);
                  categories.setVisible(false);
                  commandes.setVisible(false);
                  utilisateur.setVisible(false);
                  avis.setVisible(false);
                  callback();
            
            });
            
            utilisateursBtn.setOnMouseClicked(event -> {             
                  produits.setVisible(false);
                  jeux.setVisible(false);
                  evenements.setVisible(false);
                  sponsors.setVisible(false);
                  categories.setVisible(false);
                  commandes.setVisible(false);
                  utilisateur.setVisible(true);
                  avis.setVisible(false);
                  callback();
            
            });
            evenementsBtn.setOnMouseClicked(event -> {             
                  produits.setVisible(false);
                  jeux.setVisible(false);
                  evenements.setVisible(true);
                  sponsors.setVisible(false);
                  categories.setVisible(false);
                  commandes.setVisible(false);
                  utilisateur.setVisible(false);
                  avis.setVisible(false);
                  callback();
            
            });
            jeuxBtn.setOnMouseClicked(event -> {             
                  produits.setVisible(false);
                  jeux.setVisible(true);
                  evenements.setVisible(false);
                  sponsors.setVisible(false);
                  categories.setVisible(false);
                  commandes.setVisible(false);
                  utilisateur.setVisible(false);
                  avis.setVisible(false);
                  callback();
            
            });
            sponsorsBtn.setOnMouseClicked(event -> {             
                  produits.setVisible(false);
                  jeux.setVisible(false);
                  evenements.setVisible(false);
                  sponsors.setVisible(true);
                  categories.setVisible(false);
                  commandes.setVisible(false);
                  utilisateur.setVisible(false);
                  avis.setVisible(false);
                  callback();
            
            });
            categoriesBtn.setOnMouseClicked(event -> {             
                  produits.setVisible(false);
                  jeux.setVisible(false);
                  evenements.setVisible(false);
                  sponsors.setVisible(false);
                  categories.setVisible(true);
                  commandes.setVisible(false);
                  utilisateur.setVisible(false);
                  avis.setVisible(false);
                  callback();
            
            });
            avisBtn.setOnMouseClicked(event -> {             
                  produits.setVisible(false);
                  jeux.setVisible(false);
                  evenements.setVisible(false);
                  sponsors.setVisible(false);
                  categories.setVisible(false);
                  commandes.setVisible(false);
                  utilisateur.setVisible(false);
                  avis.setVisible(true);
                  callback();
            
            });
            commandesBtn.setOnMouseClicked(event -> {             
                  produits.setVisible(false);
                  jeux.setVisible(false);
                  evenements.setVisible(false);
                  sponsors.setVisible(false);
                  categories.setVisible(false);
                  commandes.setVisible(true);
                  utilisateur.setVisible(false);
                  avis.setVisible(false);
                  callback();
            
            });
//        pane1.setOnMouseClicked(event -> {
//
//
//
//            FadeTransition fadeTransition1=new FadeTransition(Duration.seconds(0.5),pane1);
//            fadeTransition1.setFromValue(0.15);
//            fadeTransition1.setToValue(0);
//            fadeTransition1.play();
//
//            fadeTransition1.setOnFinished(event1 -> {
//                pane1.setVisible(false);
//            });
//
//
//            TranslateTransition translateTransition1=new TranslateTransition(Duration.seconds(0.5),pane2);
//            translateTransition1.setByX(-600);
//            translateTransition1.play();
//        });
   }
   
}

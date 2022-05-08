package gui.backoffice;

import com.jfoenix.controls.JFXButton;
import entities.SessionUser;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Aymen Laroussi
 */
public class Controller implements Initializable {

    private AnchorPane pane1;
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
    
   
    private BorderPane borderpane;
    @FXML
    private JFXButton commandesBtn;
    @FXML
    private JFXButton username_session;
    @FXML
    private JFXButton utilisateursBtn1;
    @FXML
    private JFXButton commandeBtn;
    @FXML
    private JFXButton commandesBtn1;
    @FXML
    private JFXButton commandesBtn11;
    @FXML
    private ImageView produitsIcon1;
    @FXML
    private ImageView commandesIcon1;
    @FXML
    private ImageView commandesIcon12;
    @FXML
    private ImageView commandesIcon11;
    @FXML
    private Label heure;
    @FXML
    private Label user1;
    @FXML
    private Label TITLE;
    
    Stage primaryStage;
    @FXML
    private Button deconnexion;
    
    @Override
    
    public void initialize(URL location, ResourceBundle resources) {
        Timenow();
        set_usr();
        loadUI("/gui/backoffice/dashboard.fxml");
        TITLE.setText("TOURNAMENT LEGACY/ADMIN/TABLEAU DE BOARD");
        
   }
    @FXML
    private void utilisaterur(ActionEvent event) {
        loadUI("/gui/user/GestionUsersFXML.fxml");
        TITLE.setText("TOURNAMENT LEGACY/ADMIN/USER");
    }

    @FXML
    private void jeux(ActionEvent event) {
    }

    @FXML
    private void event(ActionEvent event) {
        loadUI("/gui/evenement/tableviewevent.fxml");
        TITLE.setText("TOURNAMENT LEGACY/ADMIN/EVENEMENT");
    }
    
    @FXML
    private void username_session(ActionEvent event) {
        username_session.setText(SessionUser.getInstance().getUsername().toString());
    }

    @FXML
    private void sponsors(ActionEvent event) {
         loadUI("/gui/sponsor/tableView.fxml");
        TITLE.setText("TOURNAMENT LEGACY/ADMIN/SPONSORS");
    }

    @FXML
    private void categorie(ActionEvent event) {
        loadUI("/gui/categories/TableView.fxml");
        TITLE.setText("TOURNAMENT LEGACY/ADMIN/CATEGORIES");
    }

    @FXML
    private void produits(ActionEvent event) {
        loadUI("/gui/produits/TableView.fxml");
        TITLE.setText("TOURNAMENT LEGACY/ADMIN/PRODUITS");
    }
    @FXML
    private void commande(ActionEvent event) {
        loadUI("/gui/back/Commandee.fxml");
        TITLE.setText("TOURNAMENT LEGACY/ADMIN/COMMANDES");
    }

    @FXML
    private void avis(ActionEvent event) {
        loadUI("/gui/commentaires/tableView.fxml");
        TITLE.setText("TOURNAMENT LEGACY/ADMIN/AVIS");
    }

    @FXML
    private void boutique(ActionEvent event) {
        
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/boutique/views/market.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadUI(String ui) {
    Parent root = null;
    try{
        root = FXMLLoader.load(getClass().getResource(ui));
        
   } catch (IOException ex) {
               Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
           }
    borderpane.setCenter(root);
    }
    
    private void Timenow(){
     Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e ->  
         heure.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
    ),
         new KeyFrame(Duration.seconds(1))
    );
    clock.setCycleCount(Animation.INDEFINITE);
    clock.play();
}
    
    private void set_usr() {
        user1.setText(SessionUser.getInstance().getUsername());
    }
    
    @FXML
    private void deconnexion(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/user/LoginPFXML.fxml"));
        try {
            Parent root = loader.load();
            Stage mainStage=new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
            mainStage.setTitle("Connexion");
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void ouvriLien(String url) {
    try {
    Desktop.getDesktop().browse(new URL(url).toURI());
} catch (IOException e) {
    e.printStackTrace();
} catch (URISyntaxException e) {
    e.printStackTrace();
}
}

    @FXML
    private void site(ActionEvent event) {
        ouvriLien("http://127.0.0.1:8000/home");
    }

    @FXML
    private void facebook(ActionEvent event) {
        ouvriLien("https://www.facebook.com/Tournament.Legacy.Esport/");
    }

    @FXML
    private void dashboard(ActionEvent event) {
        loadUI("/gui/backoffice/dashboard.fxml");
        TITLE.setText("TOURNAMENT LEGACY/ADMIN/TABLEAU DE BOARD");
    }


    
    }

   


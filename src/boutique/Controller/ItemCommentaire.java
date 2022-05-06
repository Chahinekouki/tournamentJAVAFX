package boutique.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import boutique.main.Main;
import boutique.main.MyListener;
import entities.Produit;
import entities.Commentaire;
import java.io.File;
import org.controlsfx.control.Rating;

/**
 *
 * @author Aymen Laroussi
 */
public class ItemCommentaire {
    private Label nameLabel;

    private Label priceLable;

    @FXML
    private ImageView img;
    @FXML
    private Label userLabel;
    @FXML
    private Rating rating;
    @FXML
    private Label dateLabel;
    @FXML
    private Label messageLabel;

    @FXML
    private void click(MouseEvent mouseEvent) {
      
    }

    private Commentaire commentaire;
    private MyListener myListener;
    float total;
    String total1;

    public void setData(Commentaire commentaire, MyListener myListener) {
        this.commentaire = commentaire;
        this.myListener = myListener;
        userLabel.setText(commentaire.getUser());
        String date= commentaire.getDate().toString();
    
        dateLabel.setText(date);
        messageLabel.setText(commentaire.getMessage());
             String A = "C:\\Pi\\public\\uploads\\1.png";
                                          File F1 = new File(A);
                                           Image image = new Image(F1.toURI().toString());
//        Image image = new Image(getClass().getResourceAsStream(produit.getImgSrc()));
        img.setImage(image);
    }
}

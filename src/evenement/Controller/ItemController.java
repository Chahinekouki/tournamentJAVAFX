package evenement.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import evenement.main.MyListener;
import entities.Evenement;
import java.io.File;

/**
 *
 * @author user
 */
public class ItemController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(evenement);
    }

    private Evenement evenement;
    private MyListener myListener;
    float total;
    String total1;
    float stq;

    public void setData(Evenement evenement, MyListener myListener) {
        this.evenement = evenement;
        this.myListener = myListener;
        nameLabel.setText(evenement.getNom());
        
      
        priceLable.setText( total1);
             String A = "C:\\Pi\\public\\uploads\\"+evenement.getImagee();
                                          File F1 = new File(A);
                                           Image image = new Image(F1.toURI().toString());
//        Image image = new Image(getClass().getResourceAsStream(evenement.getImgSrc()));
        img.setImage(image);
    }
}

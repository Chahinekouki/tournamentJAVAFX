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
import java.io.File;

public class ItemController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(produit);
    }

    private Produit produit;
    private MyListener myListener;

    public void setData(Produit produit, MyListener myListener) {
        this.produit = produit;
        this.myListener = myListener;
        nameLabel.setText(produit.getTitre());
        priceLable.setText(Main.CURRENCY + produit.getPrix());
             String A = "C:\\Pi\\public\\uploads\\"+produit.getImage();
                                          File F1 = new File(A);
                                           Image image = new Image(F1.toURI().toString());
//        Image image = new Image(getClass().getResourceAsStream(produit.getImgSrc()));
        img.setImage(image);
    }
}

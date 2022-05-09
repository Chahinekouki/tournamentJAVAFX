package boutique.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import boutique.main.Main;
import boutique.main.MyListener;
import boutique.main.MyListener1;
import entities.Categories;
import entities.Produit;
import java.io.File;

/**
 *
 * @author Aymen Laroussi
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
        myListener.onClickListener(produit);
    }
    
    @FXML
    private void click1(MouseEvent mouseEvent) {
        myListener1.onClickListener(categorie);
    }

    private Produit produit;
    private MyListener myListener;
    private Categories categorie;
    private MyListener1 myListener1;
    float total;
    String total1;

    public void setData(Produit produit, MyListener myListener) {
        this.produit = produit;
        this.myListener = myListener;
        nameLabel.setText(produit.getTitre());
        float promo=produit.getPromo() ;
        if (promo != 0){
            total = ((produit.getPromo()*produit.getPrix())/100)-produit.getPromo();
            if(total == (long) total)
                total1=String.format("%d",(long)total);
         else
                total1 =String.format("%s",total);
        }
        else{
            total=produit.getPrix(); 
        }
        priceLable.setText( total1+" TND");
             String A = "C:\\Pi\\public\\uploads\\"+produit.getImage();
                                          File F1 = new File(A);
                                           Image image = new Image(F1.toURI().toString());
//        Image image = new Image(getClass().getResourceAsStream(produit.getImgSrc()));
        img.setImage(image);
    }
    
    
     public void setData1(Categories categorie, MyListener1 myListener) {
        this.categorie = categorie;
        this.myListener1 = myListener;
        nameLabel.setText(categorie.getNom());
    }
}

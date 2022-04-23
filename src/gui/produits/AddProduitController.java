/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.produits;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entities.Produit;
import java.io.File;
import utils.MyDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author hocin
 */
public class AddProduitController implements Initializable {

  private JFXTextField nameFld;
  
  String productImage;
  String query = null;
  Connection connection = null;
  ResultSet resultSet = null;
  PreparedStatement preparedStatement;
  Produit produit = null;
  private boolean update;
  int produitId;
  @FXML
  private JFXTextField titreFld;
  @FXML
  private JFXTextArea descriptionFld;
  @FXML
  private JFXTextField promoFld;
  @FXML
  private JFXTextField stockFld;

  @FXML
  private Button ImageFld;
  @FXML
  private JFXTextField refFld;
  @FXML
  private JFXTextArea longFld;
  @FXML
  private JFXTextArea prixFld;
  @FXML
  private ComboBox flash1;
  @FXML
  private ComboBox categorie;

  /**
   * Initializes the controller class.
   */
  int categorieID = 1;
  boolean flash = true;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
    flash1.getItems().addAll("OUI", "NON");

    try {
      ResultSet rs, rs1;
      connection = MyDB.getInstance().getConnexion();
      rs = connection.createStatement().executeQuery("SELECT nom FROM categories");
      ObservableList data = FXCollections.observableArrayList();
      while (rs.next()) {
        data.add(new String(rs.getString(1)));
      }
      System.out.println(data);
      categorie.setItems(data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  private void save(MouseEvent event) throws SQLException {

    connection = MyDB.getInstance().getConnexion();
    //tsajalhom

    String titre = titreFld.getText();
    String description = descriptionFld.getText();
    String promo = promoFld.getText();
    String stock = stockFld.getText();
    String image = ImageFld.getText();
    String longdescription = longFld.getText();
    String prix = prixFld.getText();

    if (titre.isEmpty() || description.isEmpty() || promo.isEmpty() || stock.isEmpty() || image.isEmpty() || longdescription.isEmpty() || prix.isEmpty()) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText(null);
      alert.setContentText("Remplissez les champs!");
      alert.showAndWait();

    } else {
      getQuery();
      insert();
      clean();

    }

  }

  @FXML
  private void clean() {
    titreFld.setText(null);
    descriptionFld.setText(null);
    promoFld.setText(null);
    stockFld.setText(null);
    ImageFld.setText(null);
    prixFld.setText(null);
    longFld.setText(null);
    refFld.setText(null);
    flash1.valueProperty().set(null);
    categorie.valueProperty().set(null);

  }

  private void getQuery() {

    if (update == false) {

      query = "INSERT INTO `produits`(`categories_id`,`titre`, `description`,`promo`,`stock`,`flash`,`image`,`ref`,`longdescription`, `prix` ) VALUES (?,?,?,?,?,?,?,?,?,?)";

    } else {
      query = "UPDATE `produits` SET " +
        "`titre`=?," +
        "`description`=?," +
        "`promo`=?," +
        "`stock`=?," +
        "`promo`=?," +
        "`flash`=?," +
        "`image`=?," +
        "`ref`=?," +
        "`longdescription`=?," +
        "`prix`= ? WHERE id = '" + produitId + "'";
    }

  }

  private void insert() throws SQLException {

    try {

      float prix = Float.parseFloat(prixFld.getText());
      float stock = Float.parseFloat(stockFld.getText());
      preparedStatement = connection.prepareStatement(query);
      String choicecat = categorie.getValue().toString();
      try {
        ResultSet rs1;
        connection = MyDB.getInstance().getConnexion();
        rs1 = connection.createStatement().executeQuery("SELECT id FROM categories where nom like '" + choicecat + "' ORDER BY nom DESC LIMIT 1");
        ObservableList data1 = FXCollections.observableArrayList();
        while (rs1.next()) {
          data1.add(new String(rs1.getString(1)));
          System.out.println(data1.toString());
          String data2 = data1.toString();
          String data3 = (data2.substring((data2).indexOf("[") + 1, (data2).indexOf("]")));
          System.out.println(data3);
          categorieID = Integer.parseInt(data3);
        }
        System.out.println(data1);
      } catch (Exception e) {
        e.printStackTrace();
      }

      preparedStatement.setInt(1, categorieID);
      preparedStatement.setString(2, titreFld.getText());
      preparedStatement.setString(3, descriptionFld.getText());
      preparedStatement.setString(4, promoFld.getText());
      preparedStatement.setFloat(5, stock);
      String choice = flash1.getValue().toString();
      if (choice == "OUI") {
        flash = true;
      } else {
        flash = false;
      }
      preparedStatement.setBoolean(6, flash);
      preparedStatement.setString(7, ImageFld.getText());
      preparedStatement.setString(8, refFld.getText());
      preparedStatement.setString(9, longFld.getText());
      preparedStatement.setFloat(10, prix);

      preparedStatement.execute();

    } catch (SQLException ex) {
      Logger.getLogger(AddProduitController.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  void setTextField(int categorie, int id, String titre, String description, float promo, float stock, boolean flash, String image, String ref, String longdescription, float prix) {

    produitId = id;
    titreFld.setText(titre);
    descriptionFld.setText(description);
    promoFld.setText(Float.toString(promo));
    stockFld.setText(Float.toString(stock));
    ImageFld.setText(image);
    refFld.setText(ref);
    prixFld.setText(Float.toString(prix));

  }

  void setUpdate(boolean b) {
    this.update = b;

  }

    @FXML
    private void Upload(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null){
            productImage= selectedFile.getName();
           // Image i = new Image(new FileInputStream("C:\\Users\\ACER\\OneDrive\\Bureau\\PI-DEV\\public\\front\\images\\"+productImage)); 
            //imgview.setImage(i);
            ImageFld.setText(productImage);
            }else {
            System.out.println("File is not valid");
        } 
    }

}
package gui.produits;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.io.FileUtils;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entities.Produit;
import java.io.File;
import java.io.IOException;
import utils.MyDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.mail.Authenticator;

/**
 *
 * @author  Aymen Laroussi
 */
public class AddProduitController implements Initializable {

  private JFXTextField nameFld;
  
   final String username = "tournament.leegacy";
        final String password = "Aymen123456789!";
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
  String URLImage;
  String cat;

  /**
   * Initializes the controller class.
   */
  int categorieID = 1;
  boolean flash = true;
  
  public String RechercheCategorie(int id) {
   
    try {
      ResultSet rs, rs1;
      connection = MyDB.getInstance().getConnexion();
      rs = connection.createStatement().executeQuery("SELECT nom FROM categories where id=" + id + "");
      ObservableList data = FXCollections.observableArrayList();
      while (rs.next()) {
        data.add(new String(rs.getString(1)));
      }
      cat=data.toString();
      cat =(cat.substring((cat).indexOf("[")+1 ,(cat).indexOf("]")));
    } catch (Exception e) {
      e.printStackTrace();
    }
       return cat;
  }
  
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
    
    if (titre.isEmpty() || description.isEmpty() || stock.isEmpty() || image.isEmpty() || longdescription.isEmpty() || prix.isEmpty() ) {
      
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText(null);
      alert.setContentText("Remplissez les champs!");
      alert.showAndWait();
      if (!prix.matches("\\d*")||!stock.matches("\\d*")||!promo.matches("\\d*")){
          alert.setHeaderText(null);
        alert.setContentText("champ doit etre du type numerique!");
        alert.showAndWait();
      }else
     if ((Integer.parseInt(prix)<=1)|| (promo!=null)&&(Integer.parseInt(promo)<=1)||(Integer.parseInt(stock)<=1)){
        
        alert.setHeaderText(null);
        alert.setContentText("nombre doit etre positive!");
        alert.showAndWait();   
      } 
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
    ImageFld.setText("Importer image");
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
        "`categories_id`=?," +
        "`titre`=?," +
        "`description`=?," +
        "`promo`=?," +
        "`stock`=?," +
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
      
      if (choice == "OUI") {
          try {
        ResultSet rs1;
        connection = MyDB.getInstance().getConnexion();
        rs1 = connection.createStatement().executeQuery("SELECT username,email FROM user");
        String email;
        String username;
        while (rs1.next()) {
          email=(new String(rs1.getString("email")));
          username=(new String(rs1.getString("username")));
            System.out.println(email);
          
          try {
            sendMail(email,username,titreFld.getText(),promoFld.getText(),prixFld.getText());
            } catch (Exception ex) {
                 Logger.getLogger(AddProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
           String content = "Produit insérer!";
        showSuccessAlert(content);
    }
    
        
      } catch (Exception e) {
        e.printStackTrace();
      }
          
      } else {
      }
      
      
      
      
    FXMLLoader loader=new FXMLLoader(getClass().getResource("/gui/produits/tableview.fxml"));
        try {
            Parent root = (Parent) loader.load();
        
            TableViewController secController=loader.getController();
            secController.refreshTable1();
            } catch (IOException ex) {
            Logger.getLogger(AddProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }

    } catch (SQLException ex) {
      Logger.getLogger(AddProduitController.class.getName()).log(Level.SEVERE, null, ex);
    }

  }
public static void showSuccessAlert(String content)
{
    Platform.runLater(() -> {
        Alert a = new Alert(Alert.AlertType.INFORMATION, content, ButtonType.OK);
        a.show();
    });
        }
  void setTextField(int categorie1, int id, String titre, String description, float promo, float stock, boolean flash, String image, String ref, String longdescription, float prix) {

    produitId = id;
    titreFld.setText(titre);
    descriptionFld.setText(description);
    promoFld.setText(Float.toString(promo));
    stockFld.setText(Float.toString(stock));
    ImageFld.setText(image);
    if (flash==true) {
        flash1.setValue("OUI");
    }else{
    flash1.setValue("NON");
    }
    categorie.setValue(RechercheCategorie(categorie1));
    longFld.setText(longdescription);
    
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
            URLImage= selectedFile.getPath();
            productImage= selectedFile.getName();
           // Image i = new Image(new FileInputStream("C:\\Users\\ACER\\OneDrive\\Bureau\\PI-DEV\\public\\front\\images\\"+productImage)); 
            //imgview.setImage(i);
            ImageFld.setText(productImage);
            System.out.println(URLImage);
            }else {
            System.out.println("File is not valid");
        } 

        File srcFile = new File(URLImage);
        File destDir = new File("C:\\Pi\\public\\uploads");
        try {
            FileUtils.copyFileToDirectory(srcFile, destDir);
        } catch (IOException e) {
    }
         
        
    }
      
     public static void sendMail(String recepient,String username,String titre,String promo,String prix) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "tournament.leggacy@gmail.com";
        //Your gmail password
        String password = "Aymen123456789!";

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recepient,username, titre, promo, prix);

        //Send mail
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient,String username,String titre,String promo,String prix) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("NE RATTEZ PAS NOS PROMOTION!");
            float total=Float.parseFloat(prix)-((Float.parseFloat(promo)*Float.parseFloat(prix))/100);
            String total1;
              if(total == (long) total)
                    total1=String.format("%d",(long)total);
                else
                    total1 =String.format("%s",total);
            String htmlCode = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
"<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
"<head>\n" +
"<!--[if gte mso 9]>\n" +
"<xml>\n" +
"  <o:OfficeDocumentSettings>\n" +
"    <o:AllowPNG/>\n" +
"    <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
"  </o:OfficeDocumentSettings>\n" +
"</xml>\n" +
"<![endif]-->\n" +
"  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"  <meta name=\"x-apple-disable-message-reformatting\">\n" +
"  <!--[if !mso]><!--><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><!--<![endif]-->\n" +
"  <title></title>\n" +
"  \n" +
"    <style type=\"text/css\">\n" +
"      @media only screen and (min-width: 620px) {\n" +
"  .u-row {\n" +
"    width: 600px !important;\n" +
"  }\n" +
"  .u-row .u-col {\n" +
"    vertical-align: top;\n" +
"  }\n" +
"\n" +
"  .u-row .u-col-50 {\n" +
"    width: 300px !important;\n" +
"  }\n" +
"\n" +
"  .u-row .u-col-100 {\n" +
"    width: 600px !important;\n" +
"  }\n" +
"\n" +
"}\n" +
"\n" +
"@media (max-width: 620px) {\n" +
"  .u-row-container {\n" +
"    max-width: 100% !important;\n" +
"    padding-left: 0px !important;\n" +
"    padding-right: 0px !important;\n" +
"  }\n" +
"  .u-row .u-col {\n" +
"    min-width: 320px !important;\n" +
"    max-width: 100% !important;\n" +
"    display: block !important;\n" +
"  }\n" +
"  .u-row {\n" +
"    width: calc(100% - 40px) !important;\n" +
"  }\n" +
"  .u-col {\n" +
"    width: 100% !important;\n" +
"  }\n" +
"  .u-col > div {\n" +
"    margin: 0 auto;\n" +
"  }\n" +
"}\n" +
"body {\n" +
"  margin: 0;\n" +
"  padding: 0;\n" +
"}\n" +
"\n" +
"table,\n" +
"tr,\n" +
"td {\n" +
"  vertical-align: top;\n" +
"  border-collapse: collapse;\n" +
"}\n" +
"\n" +
"p {\n" +
"  margin: 0;\n" +
"}\n" +
"\n" +
".ie-container table,\n" +
".mso-container table {\n" +
"  table-layout: fixed;\n" +
"}\n" +
"\n" +
"* {\n" +
"  line-height: inherit;\n" +
"}\n" +
"\n" +
"a[x-apple-data-detectors='true'] {\n" +
"  color: inherit !important;\n" +
"  text-decoration: none !important;\n" +
"}\n" +
"\n" +
"table, td { color: #000000; } a { color: #0000ee; text-decoration: underline; } @media (max-width: 480px) { #u_content_heading_1 .v-container-padding-padding { padding: 80px 10px 10px !important; } #u_content_text_8 .v-container-padding-padding { padding: 10px 22px 70px !important; } }\n" +
"    </style>\n" +
"  \n" +
"  \n" +
"\n" +
"</head>\n" +
"\n" +
"<body class=\"clean-body u_body\" style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;background-color: #dde5e7;color: #000000\">\n" +
"  <!--[if IE]><div class=\"ie-container\"><![endif]-->\n" +
"  <!--[if mso]><div class=\"mso-container\"><![endif]-->\n" +
"  <table style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;min-width: 320px;Margin: 0 auto;background-color: #dde5e7;width:100%\" cellpadding=\"0\" cellspacing=\"0\">\n" +
"  <tbody>\n" +
"  <tr style=\"vertical-align: top\">\n" +
"    <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n" +
"    <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #dde5e7;\"><![endif]-->\n" +
"    \n" +
"\n" +
"<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
"  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">\n" +
"    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n" +
"      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: transparent;\"><![endif]-->\n" +
"      \n" +
"<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n" +
"<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
"  <div style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n" +
"  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\n" +
"  \n" +
"<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"  <tbody>\n" +
"    <tr>\n" +
"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n" +
"        \n" +
"  <table height=\"0px\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 0px solid #BBBBBB;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n" +
"    <tbody>\n" +
"      <tr style=\"vertical-align: top\">\n" +
"        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n" +
"          <span>&#160;</span>\n" +
"        </td>\n" +
"      </tr>\n" +
"    </tbody>\n" +
"  </table>\n" +
"\n" +
"      </td>\n" +
"    </tr>\n" +
"  </tbody>\n" +
"</table>\n" +
"\n" +
"  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
"  </div>\n" +
"</div>\n" +
"<!--[if (mso)|(IE)]></td><![endif]-->\n" +
"      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
"    </div>\n" +
"  </div>\n" +
"</div>\n" +
"\n" +
"\n" +
"\n" +
"<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
"  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">\n" +
"    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n" +
"      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: transparent;\"><![endif]-->\n" +
"      \n" +
"<!--[if (mso)|(IE)]><td align=\"center\" width=\"300\" style=\"background-color: #863b90;width: 300px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n" +
"<div class=\"u-col u-col-50\" style=\"max-width: 320px;min-width: 300px;display: table-cell;vertical-align: top;\">\n" +
"  <div style=\"background-color: #863b90;width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n" +
"  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\n" +
"  \n" +
"<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"  <tbody>\n" +
"    <tr>\n" +
"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:8px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n" +
"        \n" +
"<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
"  <tr>\n" +
"    <td style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\">\n" +
"      <a href=\"http://127.0.0.1:8000/home\" target=\"_blank\">\n" +
"      <img align=\"center\" border=\"0\" src=\"https://i.postimg.cc/pT91K73T/image-3.png\" alt=\"150%\" title=\"150%\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 58%;max-width: 164.72px;\" width=\"164.72\"/>\n" +
"      </a>\n" +
"    </td>\n" +
"  </tr>\n" +
"</table>\n" +
"\n" +
"      </td>\n" +
"    </tr>\n" +
"  </tbody>\n" +
"</table>\n" +
"\n" +
"  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
"  </div>\n" +
"</div>\n" +
"<!--[if (mso)|(IE)]></td><![endif]-->\n" +
"<!--[if (mso)|(IE)]><td align=\"center\" width=\"300\" style=\"background-color: #cc518b;width: 300px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n" +
"<div class=\"u-col u-col-50\" style=\"max-width: 320px;min-width: 300px;display: table-cell;vertical-align: top;\">\n" +
"  <div style=\"background-color: #cc518b;width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n" +
"  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\n" +
"  \n" +
"<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"  <tbody>\n" +
"    <tr>\n" +
"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:4px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n" +
"        \n" +
"  <h1 style=\"margin: 0px; color: #ffffff; line-height: 200%; text-align: left; word-wrap: break-word; font-weight: normal; font-family: tahoma,arial,helvetica,sans-serif; font-size: 17px;\">\n" +
"    Salut!\n" +
"  </h1>\n" +
"\n" +
"      </td>\n" +
"    </tr>\n" +
"  </tbody>\n" +
"</table>\n" +
"\n" +
"<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"  <tbody>\n" +
"    <tr>\n" +
"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:11px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n" +
"        \n" +
"  <h1 style=\"margin: 0px; color: #ffffff; line-height: 100%; text-align: left; word-wrap: break-word; font-weight: normal; font-family: arial,helvetica,sans-serif; font-size: 29px;\">\n" +
"    <strong>"+username+"</strong>\n" +
"  </h1>\n" +
"\n" +
"      </td>\n" +
"    </tr>\n" +
"  </tbody>\n" +
"</table>\n" +
"\n" +
"  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
"  </div>\n" +
"</div>\n" +
"<!--[if (mso)|(IE)]></td><![endif]-->\n" +
"      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
"    </div>\n" +
"  </div>\n" +
"</div>\n" +
"\n" +
"\n" +
"\n" +
"<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
"  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\n" +
"    <div style=\"border-collapse: collapse;display: table;width: 100%;background-image: url('https://i.ibb.co/THFXgmR/image-3.png');background-position: center top;background-color: transparent;\">\n" +
"      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-image: url('images/image-1.png');background-repeat: no-repeat;background-position: center top;background-color: #ffffff;\"><![endif]-->\n" +
"      \n" +
"<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n" +
"<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
"  <div style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n" +
"  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\n" +
"  \n" +
"<table id=\"u_content_heading_1\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"  <tbody>\n" +
"    <tr>\n" +
"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:85px 10px 10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n" +
"        \n" +
"  <h1 style=\"margin: 0px; color: #ffffff; line-height: 140%; text-align: center; word-wrap: break-word; font-weight: normal; font-family: tahoma,arial,helvetica,sans-serif; font-size: 72px;\">\n" +
"    <strong>RÉDUCTION<br>"+promo+"%</strong>\n" +
"  </h1>\n" +
"\n" +
"      </td>\n" +
"    </tr>\n" +
"  </tbody>\n" +
"</table>\n" +
"\n" +
"<table id=\"u_content_text_8\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"  <tbody>\n" +
"    <tr>\n" +
"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 10px 120px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n" +
"        \n" +
"  <div style=\"color: #ffffff; line-height: 140%; text-align: center; word-wrap: break-word;\">\n" +
"    <p style=\"font-size: 14px; line-height: 140%;\"><span style=\"font-family: tahoma, arial, helvetica, sans-serif; font-size: 14px; line-height: 19.6px;\"><strong><span style=\"font-size: 38px; line-height: 53.2px;\">Le produit "+titre+" est du "+total+"TND au lieu de <strike>"+prix+"TND</strike></span></strong></span></p>\n" +
"  </div>\n" +
"\n" +
"      </td>\n" +
"    </tr>\n" +
"  </tbody>\n" +
"</table>\n" +
"\n" +
"  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
"  </div>\n" +
"</div>\n" +
"<!--[if (mso)|(IE)]></td><![endif]-->\n" +
"      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
"    </div>\n" +
"  </div>\n" +
"</div>\n" +
"\n" +
"\n" +
"\n" +
"<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
"  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #34495e;\">\n" +
"    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n" +
"      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #34495e;\"><![endif]-->\n" +
"      \n" +
"<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"background-color: #703e87;width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n" +
"<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
"  <div style=\"background-color: #703e87;width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n" +
"  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\n" +
"  \n" +
"<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"  <tbody>\n" +
"    <tr>\n" +
"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:60px 10px 0px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n" +
"        \n" +
"  <div style=\"color: #ffd283; line-height: 150%; text-align: center; word-wrap: break-word;\">\n" +
"    <p style=\"font-size: 14px; line-height: 150%;\"><a rel=\"noopener\" href=\"http://127.0.0.1:8000/home\" target=\"_blank\">w w w . t o u r n a m e n t - l e g a c y . o n l i n e</a></p>\n" +
"  </div>\n" +
"\n" +
"      </td>\n" +
"    </tr>\n" +
"  </tbody>\n" +
"</table>\n" +
"\n" +
"<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"  <tbody>\n" +
"    <tr>\n" +
"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:27px 10px 30px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n" +
"        \n" +
"<div align=\"center\">\n" +
"  <div style=\"display: table; max-width:51px;\">\n" +
"  <!--[if (mso)|(IE)]><table width=\"51\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"border-collapse:collapse;\" align=\"center\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse; mso-table-lspace: 0pt;mso-table-rspace: 0pt; width:51px;\"><tr><![endif]-->\n" +
"  \n" +
"    \n" +
"    <!--[if (mso)|(IE)]><td width=\"32\" style=\"width:32px; padding-right: 0px;\" valign=\"top\"><![endif]-->\n" +
"    <table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"32\" height=\"32\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;margin-right: 0px\">\n" +
"      <tbody><tr style=\"vertical-align: top\"><td align=\"left\" valign=\"middle\" style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n" +
"        <a href=\"https://www.facebook.com/Tournament.Legacy.Esport\" title=\"Facebook\" target=\"_blank\">\n" +
"          <img src=\"https://i.postimg.cc/ZR3CBYY5/image-2.png\" alt=\"Facebook\" title=\"Facebook\" width=\"32\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block !important;border: none;height: auto;float: none;max-width: 32px !important\">\n" +
"        </a>\n" +
"      </td></tr>\n" +
"    </tbody></table>\n" +
"    <!--[if (mso)|(IE)]></td><![endif]-->\n" +
"    \n" +
"    \n" +
"    <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
"  </div>\n" +
"</div>\n" +
"\n" +
"      </td>\n" +
"    </tr>\n" +
"  </tbody>\n" +
"</table>\n" +
"\n" +
"<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"  <tbody>\n" +
"    <tr>\n" +
"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 30px 50px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n" +
"        \n" +
"  <div style=\"color: #e7e7e7; line-height: 200%; text-align: center; word-wrap: break-word;\">\n" +
"    <p style=\"font-size: 14px; line-height: 200%;\">Manage your esports competition with the right tools<br />Toornament Legacy is a suite of powerful tools for organizers, agencies, studios and publishers to manage their tournaments and leagues.</p>\n" +
"  </div>\n" +
"\n" +
"      </td>\n" +
"    </tr>\n" +
"  </tbody>\n" +
"</table>\n" +
"\n" +
"  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
"  </div>\n" +
"</div>\n" +
"<!--[if (mso)|(IE)]></td><![endif]-->\n" +
"      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
"    </div>\n" +
"  </div>\n" +
"</div>\n" +
"\n" +
"\n" +
"\n" +
"<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
"  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">\n" +
"    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n" +
"      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: transparent;\"><![endif]-->\n" +
"      \n" +
"<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n" +
"<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
"  <div style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n" +
"  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\n" +
"  \n" +
"<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"  <tbody>\n" +
"    <tr>\n" +
"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:15px 10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n" +
"        \n" +
"  <div style=\"color: #7e8c8d; line-height: 140%; text-align: center; word-wrap: break-word;\">\n" +
"    <p style=\"font-size: 14px; line-height: 140%;\"><span style=\"font-size: 14px; line-height: 19.6px; font-family: arial, helvetica, sans-serif;\">&copy;2022 Tournament Legacy | 17 nahj la3me w dhas, 7ay loutani</span></p>\n" +
"  </div>\n" +
"\n" +
"      </td>\n" +
"    </tr>\n" +
"  </tbody>\n" +
"</table>\n" +
"\n" +
"  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
"  </div>\n" +
"</div>\n" +
"<!--[if (mso)|(IE)]></td><![endif]-->\n" +
"      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
"    </div>\n" +
"  </div>\n" +
"</div>\n" +
"\n" +
"\n" +
"    <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
"    </td>\n" +
"  </tr>\n" +
"  </tbody>\n" +
"  </table>\n" +
"  <!--[if mso]></div><![endif]-->\n" +
"  <!--[if IE]></div><![endif]-->\n" +
"</body>\n" +
"\n" +
"</html>";
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(AddProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
     return null;
    }

    
    
   

}
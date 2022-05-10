/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;
import entities.Evenement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDB;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import evenement.Controller.MarketController;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;




/**
 * FXML Controller class
 *
 * @author user
 */
public class EventViewController implements Initializable {
 String idevent;
  int aff=0;
    String lat,lon;
    @FXML
    private Label testlaba;
    @FXML
    private Label testlaba1;
    @FXML
    private Label local;
    @FXML
    private WebView infopage;
    @FXML
    private WebView mapview;
    @FXML
    private ImageView imgqrcode;
    @FXML
    private ImageView image;
    @FXML
    private Label testlaba2;
    @FXML
    private Label testlaba3;
    @FXML
    private Label testlaba31;
    @FXML
    private Label testlaba11;
    @FXML
    private Label testlaba12;
    @FXML
    private Label testlaba111;
    @FXML
    private Label lieu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
  infopage.setVisible(false);
        
           
        ObservableList<Evenement> booksList = FXCollections.observableArrayList();
    	   Connection connection = MyDB.getInstance().getConnexion();
    	String query = "SELECT * FROM evenement where id="+MarketController.iddata;
    	   Statement st;
    	   ResultSet rs;
    	
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Evenement books;
			while(rs.next()) {
                       
				
                                 idevent=(rs.getString("id").toString());
			testlaba.setText(rs.getString("nomeven").toString());
                        testlaba1.setText(rs.getString("lieuevent").toString());
                        testlaba3.setText(rs.getString("datevent").toString());
                        testlaba31.setText(rs.getString("datefin").toString());
                        testlaba2.setText(rs.getString("heurevent").toString());
                         local.setText(rs.getString("description").toString());
                        lat=rs.getString("lat").toString();
                        lon=rs.getString("longi").toString();
                        String   image1=rs.getString("image").toString();
                           String A = "C:\\Pi\\public\\uploads\\"+image1;
                                          File F1 = new File(A);
                                           Image image12 = new Image(F1.toURI().toString());
//        Image image = new Image(getClass().getResourceAsStream(evenement.getImgSrc()));
        image.setImage(image12);
                        }
                        
		} catch (Exception e) {
			e.printStackTrace();
		}
        
      
       
      
         WebEngine webEngine = mapview.getEngine();

  /* Charge la carte HTML avec Leafletjs */
  webEngine.loadContent("<!DOCTYPE html>\n" +
"<html>\n" +
"<head>\n" +
          
"    <title>Google Maps Drag Marker Get Coordinates</title>\n" +
"    <script src=\"https://code.jquery.com/jquery-3.5.1.min.js\" type=\"text/javascript\"></script>\n" +
"    <script type=\"text/javascript\" src=\"https://maps.googleapis.com/maps/api/js?key=AIzaSyBvHg2E3bOHns4yCQJ4ogiFR9wllEg4Z0E\"></script>\n" +
"    <script type=\"text/javascript\">\n" +
"        function initialize() {\n" +
"            // Creating map object\n" +
"            var map = new google.maps.Map(document.getElementById('map_canvas'), {\n" +
"                zoom: 12,\n" +
"                center: new google.maps.LatLng("+lat+","+ lon+"),\n" +
"                mapTypeId: google.maps.MapTypeId.ROADMAP\n" +
"            });\n" +
"            // creates a draggable marker to the given coords\n" +
"            var vMarker = new google.maps.Marker({\n" +
"                position: new google.maps.LatLng("+lat+","+ lon+"),\n" +
"                draggable: true\n" +
"            });\n" +
"            // adds a listener to the marker\n" +
"            // gets the coords when drag event ends\n" +
"            // then updates the input with the new coords\n" +
"           \n" +
"            // centers the map on markers coords\n" +
"            map.setCenter(vMarker.position);\n" +
"            // adds the marker on the map\n" +
"            vMarker.setMap(map);\n" +
"        }\n" +
"        \n" +
"    </script>\n" +
"</head>\n" +
"<body onload=\"initialize();\">\n" +
"   \n" +
"    <div id=\"map_canvas\" style=\"width: auto; height: 345px;\">\n" +
"    </div>\n" +
"</body>\n" +
"</html>");
  
  
  
  
        
  
  //////////qrcode
  
  
           QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String myWeb = "\"http://127.0.0.1:8000/admin/evenements/"+ idevent.toString();
        int width = 300;
        int height = 300;
        String fileType = "png";
        
        BufferedImage bufferedImage = null;
        
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            
            
        } catch (WriterException ex) {
            System.out.println("erreur");
        }
        
        
        ImageView qrView = new ImageView();
        imgqrcode.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
     }   

    @FXML
    private void infobtn(ActionEvent event) {
 WebEngine webEngine = infopage.getEngine();

  /* Charge la carte HTML avec Leafletjs */
  webEngine.load("https://aicha111.000webhostapp.com/chat.php");
        if (aff==0){
        infopage.setVisible(true);
        aff=1;
        }
        else{
              aff=0;
             infopage.setVisible(false);
        }
     
    
   }
    }
    
    
    


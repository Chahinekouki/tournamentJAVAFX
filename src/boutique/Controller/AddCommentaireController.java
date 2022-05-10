package boutique.Controller;

import com.jfoenix.controls.JFXButton;
import gui.commentaires.*;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entities.Commentaire;
import entities.SessionUser;
import utils.MyDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Aymen Laroussi
 */
public class AddCommentaireController implements Initializable {

        
    int user;
    int produitID;
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Commentaire commentaire = null;
    private boolean update;
    int commentaireId;
    private ComboBox<?> userFld;
    private ComboBox<?> produitFld;
    @FXML
    private JFXTextArea messageFld;
    private Date date;
    String idproduit;
    @FXML
    private VBox avisvbox;
    @FXML
    private JFXButton save;
    
    Connection connexion;
    Statement stm;
    double rate1;
    int produit;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int idd= SessionUser.getInstance().getId();
                 System.out.println(idd);
        try {
            ResultSet rs,rs1;
            connection = MyDB.getInstance().getConnexion();
            rs = connection.createStatement().executeQuery("SELECT email FROM user");
            ObservableList data = FXCollections.observableArrayList();
        while (rs.next()){
            data.add(new String(rs.getString(1)));
        }
        System.out.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }

    @FXML
    private void save(MouseEvent event) throws SQLException {

        connection = MyDB.getInstance().getConnexion();
        String message = messageFld.getText();
        

        if (message.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Remplisez tous les champs!");
            alert.showAndWait();

        } else {
            getQuery();
            insert();
            Stage stage = (Stage) save.getScene().getWindow();
            stage.close();
        }

    }

    @FXML
    private void clean() {
        messageFld.setText(null);
        
        
    }

    private void getQuery() {

        if (update == false) {
            
            query = "INSERT INTO `commentaires`(`user_id`,`produit_id`,`message`,`date`) VALUES (?,?,?,?)";

        }else{
            query = "UPDATE `commentaires` SET "
                    + "`user_id`=?,"
                    + "`produit_id`=?,"
                    + "`message`=?,"
                    + "`date`=? WHERE id = '"+commentaireId+"'";
        }

    }

    private void insert() throws SQLException{
        Date date1 = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        
        Calendar calendarInstance = Calendar.getInstance();
        
        java.sql.Timestamp date = new java.sql.Timestamp(
                calendarInstance.getTime().getTime());
        System.out.println(date);
        try {
            preparedStatement = connection.prepareStatement(query);
            
            String mail =  SessionUser.getInstance().getEmail();
                 System.out.println(mail);
             try {
            ResultSet rs1;
            connection = MyDB.getInstance().getConnexion();
            rs1 = connection.createStatement().executeQuery("SELECT id FROM user where email like '"+mail+"' ORDER BY email DESC LIMIT 1");
            ObservableList data1 = FXCollections.observableArrayList();
        while (rs1.next()){
            data1.add(new String(rs1.getString(1)));
            String data2=data1.toString();
            String data3 =(data2.substring((data2).indexOf("[")+1 ,(data2).indexOf("]")));
            
            user =Integer.parseInt(data3);
            System.err.println(user);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user);
            preparedStatement.setInt(2, Integer.parseInt(idproduit));
            preparedStatement.setString(3, messageFld.getText());
            preparedStatement.setTimestamp(4,date);
            
            preparedStatement.execute();
            
             
            System.out.println(rate1);
            System.out.println(produit);
            System.out.println(user);
            
            query = "INSERT INTO `rating`(`rating`,`entity_code`,`user_code`) VALUES (?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, rate1);
            preparedStatement.setInt(2, produit);
            preparedStatement.setInt(3, user);
            
            preparedStatement.execute();
            
//            String req1 = "INSERT INTO `rating` (`rating`,`entity_code`, `user_code` ) VALUES ( '"
//                + rate1+ "', '" + produit+ "', '"+ user+ "') ";
//            stm = connexion.createStatement();
//            stm.execute(req1);

        } catch (SQLException ex) {
            Logger.getLogger(AddCommentaireController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    

   
    
    public void Rating(double comm,String id,String randomHex ){
         idproduit=id;
         rate1=comm;
        
        produit=Integer.valueOf(id);
        avisvbox.setStyle("-fx-background-color: #" + randomHex + ";\n" +
                "    -fx-background-radius: 30;");
    }

}

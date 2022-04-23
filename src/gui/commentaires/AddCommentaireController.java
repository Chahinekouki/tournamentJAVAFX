/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.commentaires;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entities.Commentaire;
import utils.MyDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

/**
 * FXML Controller class
 *
 * @author hocin
 */
public class AddCommentaireController implements Initializable {

    @FXML
    private JFXTextField nameFld;
    
    int user;
    int produitID;
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Commentaire commentaire = null;
    private boolean update;
    int commentaireId;
    @FXML
    private ComboBox<?> userFld;
    @FXML
    private ComboBox<?> produitFld;
    @FXML
    private JFXTextArea messageFld;
    private Date date;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ResultSet rs,rs1;
            connection = MyDB.getInstance().getConnexion();
            rs = connection.createStatement().executeQuery("SELECT email FROM user");
            rs1 = connection.createStatement().executeQuery("SELECT titre FROM produits");
            ObservableList data = FXCollections.observableArrayList();
        while (rs.next()){
            data.add(new String(rs.getString(1)));
        }
        System.out.println(data);
        userFld.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
         try {
            ResultSet rs,rs1;
            connection = MyDB.getInstance().getConnexion();
            
            rs1 = connection.createStatement().executeQuery("SELECT titre FROM produits");
            ObservableList data1 = FXCollections.observableArrayList();
        while (rs1.next()){
            data1.add(new String(rs1.getString(1)));
        }
        System.out.println(data1);
        produitFld.setItems(data1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void save(MouseEvent event) throws SQLException {

        connection = MyDB.getInstance().getConnexion();
        String message = messageFld.getText();
        

        if (message.isEmpty()||userFld.getSelectionModel().isEmpty()||produitFld.getSelectionModel().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Remplisez tous les champs!");
            alert.showAndWait();

        } else {
            getQuery();
            insert();
            clean();
        }

    }

    @FXML
    private void clean() {
        messageFld.setText(null);
        userFld.valueProperty().set(null);
        produitFld.valueProperty().set(null);
        
        
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
        
        java.sql.Timestamp date
            = new java.sql.Timestamp(
                calendarInstance.getTime().getTime());
        System.out.println(date);
        try {
            preparedStatement = connection.prepareStatement(query);
            String mail = userFld.getValue().toString();
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
        
        String produit = produitFld.getValue().toString();
             try {
            ResultSet rs1;
            connection = MyDB.getInstance().getConnexion();
            rs1 = connection.createStatement().executeQuery("SELECT id FROM produits where titre like '"+produit+"' ORDER BY titre DESC LIMIT 1");
            ObservableList data1 = FXCollections.observableArrayList();
        while (rs1.next()){
            data1.add(new String(rs1.getString(1)));
            //test
            String data2=data1.toString();
            String data3 =(data2.substring((data2).indexOf("[")+1 ,(data2).indexOf("]")));
            
            produitID =Integer.parseInt(data3);
            System.out.println(produitID);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
            
            
             
            
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user);
            preparedStatement.setInt(2, produitID);
            preparedStatement.setString(3, messageFld.getText());
            preparedStatement.setTimestamp(4,date);
            
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AddCommentaireController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void setTextField(int id, int user_id,int produit_id,String message,Date date) {
        
        String user = userFld.getValue().toString();
        String produit = produitFld.getValue().toString();
        
        commentaireId = id;
        messageFld.setText(message); 

    }

    void setUpdate(boolean b) {
        this.update = b;

    }

}

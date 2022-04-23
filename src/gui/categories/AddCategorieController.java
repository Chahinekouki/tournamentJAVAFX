/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.categories;

import com.jfoenix.controls.JFXTextField;
import entities.Categories;
import utils.MyDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author hocin
 */
public class AddCategorieController implements Initializable {

    @FXML
    private JFXTextField nameFld;
    

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Categories categorie = null;
    private boolean update;
    int categorieId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void save(MouseEvent event) {

        connection = MyDB.getInstance().getConnexion();
        String name = nameFld.getText();
        

        if (name.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Remplissez les champs");
            alert.showAndWait();

        } else {
            getQuery();
            insert();
            clean();

        }

    }

    @FXML
    private void clean() {
        nameFld.setText(null);
        
        
    }

    private void getQuery() {

        if (update == false) {
            
            query = "INSERT INTO `categories`(`nom`) VALUES (?)";

        }else{
            query = "UPDATE `categories` SET "
                    + "`nom`=? WHERE id = '"+categorieId+"'";
        }

    }

    private void insert() {

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nameFld.getText());
            
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AddCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void setTextField(int id, String name) {

        categorieId = id;
        nameFld.setText(name);
       

    }

    void setUpdate(boolean b) {
        this.update = b;

    }

}

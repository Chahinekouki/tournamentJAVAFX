/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.categories;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import entities.Categories;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Aymen Laroussi
 */
public class CategoriesFXMLController implements Initializable {

    
    @FXML
    private TableView<Categories> categoriesTable;
    @FXML
    private TableColumn<Categories, String> idCol;
    @FXML
    private TableColumn<Categories, String> nomCol;
    
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Categories student = null ;
    ObservableList<Categories>  StudentList = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
    }    
    
      @FXML
    private void refreshTable() {
        try {
            StudentList.clear();
            
            query = "SELECT * FROM `categories`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                StudentList.add(new  Categories(
                        resultSet.getInt("id"),
                        resultSet.getString("nom")));
                categoriesTable.setItems(StudentList);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoriesFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

    
     private void loadDate() {
         connection = MyDB.getInstance().getConnexion();
        refreshTable();
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        
        //add cell of button edit 
        
       
         
    }
    
}

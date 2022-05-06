package gui.backoffice;

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
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Aymen Laroussi
 */
public class dashboardController implements Initializable {

  

  String data3;
  String query = null;
  String query1 = null;
  Connection connection = null;
  PreparedStatement preparedStatement = null;
  ResultSet resultSet = null;
  PreparedStatement preparedStatement1 = null;
  ResultSet resultSet1 = null;
  


    @FXML
    private Label cat;
    @FXML
    private Label prod;
    @FXML
    private Label avi;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
    loadDate();
  }

  private void close(MouseEvent event) {
    Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
    stage.close();
  }

 

  private void refreshTable() {
    try {

      
      query = "SELECT COUNT(*) FROM categories;";
      preparedStatement = connection.prepareStatement(query);
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        cat.setText(String.valueOf(resultSet.getInt(1))); }
      System.out.println(resultSet);

    } catch (SQLException ex) {
      Logger.getLogger(dashboardController.class.getName()).log(Level.SEVERE, null, ex);
    }
     try {

      
      query = "SELECT COUNT(*) FROM produits;";
      preparedStatement = connection.prepareStatement(query);
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        prod.setText(String.valueOf(resultSet.getInt(1))); }
      System.out.println(resultSet);

    } catch (SQLException ex) {
      Logger.getLogger(dashboardController.class.getName()).log(Level.SEVERE, null, ex);
    }
      try {

      
      query = "SELECT COUNT(*) FROM commentaires;";
      preparedStatement = connection.prepareStatement(query);
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        avi.setText(String.valueOf(resultSet.getInt(1))); }
      System.out.println(resultSet);

    } catch (SQLException ex) {
      Logger.getLogger(dashboardController.class.getName()).log(Level.SEVERE, null, ex);
    }

  }


  private void loadDate() {

    connection = MyDB.getInstance().getConnexion();
    refreshTable();

  

  }

}
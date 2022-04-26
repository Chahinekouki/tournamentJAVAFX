package gui.commentaires;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Commentaire;
import utils.MyDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 *
 * @author Aymen Laroussi
 */
public class TableViewController implements Initializable {

  @FXML
  private TableView < Commentaire > commentairesTable;
  @FXML
  private TableColumn < Commentaire, String > idCol;
  @FXML
  private TableColumn < ? , ? > userCol;
  @FXML
  private TableColumn < ? , ? > produitCol;
  @FXML
  private TableColumn < ? , ? > dateCol;
  @FXML
  private TableColumn < Commentaire, String > editCol;

  String data3;
  String query = null;
  String query1 = null;
  Connection connection = null;
  PreparedStatement preparedStatement = null;
  ResultSet resultSet = null;
  PreparedStatement preparedStatement1 = null;
  ResultSet resultSet1 = null;
  Commentaire commentaires = null;

  ObservableList < Commentaire > CommentaireList = FXCollections.observableArrayList();
    ObservableList < Commentaire > CommentaireList1 = FXCollections.observableArrayList();

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
    loadDate();
  }

  @FXML
  private void close(MouseEvent event) {
    Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
    stage.close();
  }

  @FXML
  private void getAddView(MouseEvent event) {
    try {
      Parent parent = FXMLLoader.load(getClass().getResource("/gui/commentaires/addCommentaire.fxml"));
      Scene scene = new Scene(parent);
      Stage stage = new Stage();
      stage.setScene(scene);
      stage.initStyle(StageStyle.UTILITY);
      stage.show();
    } catch (IOException ex) {
      Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  @FXML
  private void refreshTable() {
    try {
      CommentaireList.clear();

      
      query = "select u.username,c.id,c.user_id,c.message,p.titre,p.id,c.produit_id,c.date from `commentaires` c , `user` u,`produits` p where u.id=c.user_id AND p.id=c.produit_id ;";
      preparedStatement = connection.prepareStatement(query);
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        
        CommentaireList.add(new Commentaire(resultSet.getInt("id"),
          
          resultSet.getString("username"),
          resultSet.getString("titre"),
          resultSet.getString("message"),
          resultSet.getDate("date")));
       
        commentairesTable.setItems(CommentaireList);

      }
      System.out.println(resultSet);

    } catch (SQLException ex) {
      Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  @FXML
  private void print(MouseEvent event) {}

  private void loadDate() {

    connection = MyDB.getInstance().getConnexion();
    refreshTable();

    idCol.setCellValueFactory(new PropertyValueFactory < > ("id"));
    userCol.setCellValueFactory(new PropertyValueFactory < > ("user"));
    produitCol.setCellValueFactory(new PropertyValueFactory < > ("produit"));
    dateCol.setCellValueFactory(new PropertyValueFactory < > ("date"));

    //add cell of button edit 
    Callback < TableColumn < Commentaire, String > , TableCell < Commentaire, String >> cellFoctory = (TableColumn < Commentaire, String > param) -> {
      // make cell containing buttons
      final TableCell < Commentaire,
      String > cell = new TableCell < Commentaire,
      String > () {
        @Override
        public void updateItem(String item, boolean empty) {
          super.updateItem(item, empty);
          //that cell created only on non-empty rows
          if (empty) {
            setGraphic(null);
            setText(null);

          } else {

            FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
            FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

            deleteIcon.setStyle(
              " -fx-cursor: hand ;" +
              "-glyph-size:28px;" +
              "-fx-fill:#ff1744;"
            );
            editIcon.setStyle(
              " -fx-cursor: hand ;" +
              "-glyph-size:28px;" +
              "-fx-fill:#00E676;"
            );
            deleteIcon.setOnMouseClicked((MouseEvent event) -> {

              ButtonType oui = new ButtonType("NON", ButtonBar.ButtonData.OK_DONE);
              ButtonType non = new ButtonType("OUI", ButtonBar.ButtonData.CANCEL_CLOSE);
              Alert alert = new Alert(Alert.AlertType.WARNING,
                "Vous etes sure de vouloir supprimer? ",
                oui,
                non);
              alert.setTitle("CONFIRMATION");
              Optional < ButtonType > result = alert.showAndWait();
              if (result.orElse(oui) == non) {
                try {
                  commentaires = commentairesTable.getSelectionModel().getSelectedItem();
                  query = "DELETE FROM `produits` WHERE id  =" + commentaires.getId();
                  connection = MyDB.getInstance().getConnexion();
                  preparedStatement = connection.prepareStatement(query);
                  preparedStatement.execute();
                  refreshTable();
                } catch (SQLException ex) {
                  Logger.getLogger(gui.produits.TableViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
              }
            });

            HBox managebtn = new HBox(deleteIcon);
            managebtn.setStyle("-fx-alignment:center");
            HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
            HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
            setGraphic(managebtn);
            setText(null);

          }
        }

      };

      return cell;
    };
    editCol.setCellFactory(cellFoctory);
    commentairesTable.setItems(CommentaireList);

  }

}
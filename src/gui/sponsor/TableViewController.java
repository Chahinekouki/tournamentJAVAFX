/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.sponsor;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Sponsor;
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
import javafx.scene.control.Alert.AlertType;
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
 * FXML Controller class
 *
 * @author hocin
 */
public class TableViewController implements Initializable {

    @FXML
    private TableView<Sponsor> sponsorsTable;
    @FXML
    private TableColumn<Sponsor, String> idCol;
    @FXML
    private TableColumn<Sponsor, String> editCol;
    
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Sponsor sponsors = null ;
    
    ObservableList<Sponsor>  SponsorList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> nomCol;
    @FXML
    private TableColumn<?, ?> prenomCol;
    @FXML
    private TableColumn<?, ?> numCol;
    @FXML
    private TableColumn<?, ?> budgetCol;
    @FXML
    private TableColumn<?, ?> imageCol;
    @FXML
    private TableColumn<?, ?> promoCol;

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
            Parent parent = FXMLLoader.load(getClass().getResource("/gui/sponsor/addSponsor.fxml"));
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
            SponsorList.clear();
            
            query = "SELECT * FROM `sponsors`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                SponsorList.add(new  Sponsor(
                    resultSet.getInt("id"),//or rst.getInt(1)
                    resultSet.getString("nom"),
                    resultSet.getString("prenom"),
                    resultSet.getInt("num"),
                    resultSet.getFloat("budget"),
                    resultSet.getString("image")));
                        
                sponsorsTable.setItems(SponsorList);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

    @FXML
    private void print(MouseEvent event) {
    }

    private void loadDate() {
        
        connection = MyDB.getInstance().getConnexion();
        
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        numCol.setCellValueFactory(new PropertyValueFactory<>("num"));
        budgetCol.setCellValueFactory(new PropertyValueFactory<>("budget"));
        
        imageCol.setCellValueFactory(new PropertyValueFactory<>("image"));
        
        
        //add cell of button edit 
         Callback<TableColumn<Sponsor, String>, TableCell<Sponsor, String>> cellFoctory = (TableColumn<Sponsor, String> param) -> {
            // make cell containing buttons
            final TableCell<Sponsor, String> cell = new TableCell<Sponsor, String>() {
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
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            ButtonType oui = new ButtonType("NON", ButtonBar.ButtonData.OK_DONE);
                            ButtonType non = new ButtonType("OUI", ButtonBar.ButtonData.CANCEL_CLOSE);
                            Alert alert = new Alert(AlertType.WARNING,
                                    "The format for dates is year.month.day. ",
                                    oui,
                                    non);

                            alert.setTitle("Date format warning");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.orElse(oui) == non) {
                                 try {
                                sponsors = sponsorsTable.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `sponsors` WHERE id  ="+sponsors.getId();
                                connection = MyDB.getInstance().getConnexion();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                refreshTable();
                                
                            } catch (SQLException ex) {
                                Logger.getLogger(gui.sponsor.TableViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            }
                            
                            
                           

                          

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            sponsors = sponsorsTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/gui/sponsors/addSponsor.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            AddSponsorController addSponsorController = loader.getController();
                            addSponsorController.setUpdate(true);
                            addSponsorController.setTextField(sponsors.getId(),
                                    sponsors.getNom(),
                                    sponsors.getPrenom(),
                                    sponsors.getNum(),
                                    sponsors.getBudget(),
                                    sponsors.getImage());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            

                           

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
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
         sponsorsTable.setItems(SponsorList);
         
         
    }
    
}

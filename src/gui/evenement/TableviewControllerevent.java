/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.evenement;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Evenement;
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
 * @author user
 */
public class TableviewControllerevent implements Initializable {
 @FXML
    private TableView<Evenement> tableevent;
    @FXML
    private TableColumn<Evenement, String> idCol;
    @FXML
    private TableColumn<Evenement, String> editCol;
    
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Evenement evenement = null ;
    
    ObservableList<Evenement>  eventList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Evenement,String > nomCol;
    @FXML
    private TableColumn<Evenement,String > lieuCol;
    @FXML
    private TableColumn<Evenement,String > datedebCol;
    @FXML
    private TableColumn<Evenement,String > heureCol;
    @FXML
    private TableColumn<Evenement,String > datefinCol;
    @FXML
    private TableColumn<Evenement,Integer> nbrCol;
    @FXML
    private TableColumn<Evenement,String > imageCol;
    @FXML
    private TableColumn<Evenement,String > descCol;
    @FXML
    private TableColumn<Evenement,String > latCol;
    @FXML
    private TableColumn<Evenement,String > longiCol;
    
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
            Parent parent = FXMLLoader.load(getClass().getResource("/gui/evenement/addevent.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TableviewControllerevent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void refreshTable() {
        try {
            eventList.clear();
            
            query = "SELECT * FROM `evenement`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                eventList.add(new  Evenement (
                    resultSet.getInt("id"),//or rst.getInt(1)
                    resultSet.getString("nomeven"),
                    resultSet.getString("lieuevent"),
                    resultSet.getString("datevent"),
                    resultSet.getString("heurevent"),
                    resultSet.getString("datefin"),
                    resultSet.getInt("nbrplace"),
                    resultSet.getString("image"),
                    resultSet.getString("description"),
                    resultSet.getString("lat"),
                    resultSet.getString("longi")));
                        
                tableevent.setItems(eventList);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TableviewControllerevent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }


    private void loadDate() {
        
        connection = MyDB.getInstance().getConnexion();
        refreshTable();
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        lieuCol.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        datedebCol.setCellValueFactory(new PropertyValueFactory<>("datedeb"));
        heureCol.setCellValueFactory(new PropertyValueFactory<>("heure"));
        datefinCol.setCellValueFactory(new PropertyValueFactory<>("datefin"));
       nbrCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        imageCol.setCellValueFactory(new PropertyValueFactory<>("imagee"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
        latCol.setCellValueFactory(new PropertyValueFactory<>("lat"));
        
        longiCol.setCellValueFactory(new PropertyValueFactory<>("longi"));
          
        
        
        //add cell of button edit 
         Callback<TableColumn<Evenement, String>, TableCell<Evenement, String>> cellFoctory = (TableColumn<Evenement, String> param) -> {
            // make cell containing buttons
            final TableCell<Evenement, String> cell = new TableCell<Evenement, String>() {
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
                                    "Etes vous sur de supprimer cet evenement? ",
                                    oui,
                                    non);

                            alert.setTitle("Avertissement");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.orElse(oui) == non) {
                                 try {
                                evenement = tableevent.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `evenement` WHERE id  ="+evenement.getId();
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
                            
                            evenement = tableevent.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/gui/evenement/addevent.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(TableviewControllerevent.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            AddeventController addeventController = loader.getController();
                            addeventController.setUpdate(true);
                            addeventController.setTextField(
                                    evenement.getId(),
                                    evenement.getNom(),
                                    evenement.getLieu(),
                                    evenement.getDatedeb(),
                                    evenement.getHeure(),
                                    evenement.getDatefin(),
                                    evenement.getNombre(),
                                    evenement.getImagee(),
                                    evenement.getDesc(),
                                    evenement.getLat(),
                                    evenement.getLongi());
                            System.out.println(evenement);
                                    
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
         tableevent.setItems(eventList);
         
         
    }
    
}


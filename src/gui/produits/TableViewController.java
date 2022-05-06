package gui.produits;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Produit;
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
 *
 * @author  Aymen Laroussi
 */
public class TableViewController implements Initializable {

    @FXML
    private TableView<Produit> produitsTable;
    @FXML
    private TableColumn<Produit, String> idCol;
    @FXML
    private TableColumn<Produit, String> editCol;
    
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Produit produits = null ;
    
    ObservableList<Produit>  ProduitList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> titreCol;
    @FXML
    private TableColumn<?, ?> refCol;
    @FXML
    private TableColumn<?, ?> stockCol;
    @FXML
    private TableColumn<?, ?> flashCol;
    @FXML
    private TableColumn<?, ?> prixCol;
    @FXML
    private TableColumn<?, ?> promoCol;
    @FXML
    private TableColumn<?, ?> catCol;
    

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
            Parent parent = FXMLLoader.load(getClass().getResource("/gui/produits/addProduit.fxml"));
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
            ProduitList.clear();
            
            query = "SELECT p.id,p.categories_id,p.titre,p.description,p.promo,p.stock,p.flash,p.image,p.ref,p.longdescription,p.prix,c.id,c.nom FROM `produits` p,`categories` c where c.id=p.categories_id";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                ProduitList.add(new  Produit(
                    resultSet.getInt("id"),//or rst.getInt(1)
                    resultSet.getInt("categories_id"),
                    resultSet.getString("titre"),
                    resultSet.getString("description"),
                    resultSet.getString("nom"),
                    resultSet.getFloat("promo"),
                    resultSet.getFloat("stock"),
                    resultSet.getBoolean("flash"),
                    resultSet.getString("image"),
                    resultSet.getString("ref"),
                    resultSet.getString("longdescription"),
                    resultSet.getFloat("prix")));
                        
                produitsTable.setItems(ProduitList);
                
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
        refreshTable();
        
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        catCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        titreCol.setCellValueFactory(new PropertyValueFactory<>("titre"));
        refCol.setCellValueFactory(new PropertyValueFactory<>("ref"));
        stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        flashCol.setCellValueFactory(new PropertyValueFactory<>("flash"));
        prixCol.setCellValueFactory(new PropertyValueFactory<>("prix"));
        promoCol.setCellValueFactory(new PropertyValueFactory<>("promo"));
        
        //add cell of button edit 
         Callback<TableColumn<Produit, String>, TableCell<Produit, String>> cellFoctory = (TableColumn<Produit, String> param) -> {
            // make cell containing buttons
            final TableCell<Produit, String> cell = new TableCell<Produit, String>() {
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
                                    "Vous etes sure de vouloir supprimer? ",
                oui,
                non);
              alert.setTitle("CONFIRMATION");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.orElse(oui) == non) {
                                 try {
                                produits = produitsTable.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `produits` WHERE id  ="+produits.getId();
                                connection = MyDB.getInstance().getConnexion();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                refreshTable();
                                
                            } catch (SQLException ex) {
                                Logger.getLogger(gui.produits.TableViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            }
                        });
                        
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            produits = produitsTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/gui/produits/addProduit.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            AddProduitController addProduitController = loader.getController();
                            addProduitController.setUpdate(true);
                            addProduitController.setTextField(produits.getCategorie(),
                                    produits.getId(),
                                    produits.getTitre(),
                                    produits.getDescription(),
                                    produits.getPromo(),
                                    produits.getStock(),
                                    produits.getFlash(),
                                    produits.getImage(),
                                    produits.getRef(),
                                    produits.getLongdescription(),
                                    produits.getPrix());
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
        produitsTable.setItems(ProduitList);
            
    }
    
    public void refreshTable1() {
        refreshTable();   
        System.out.println("done!");
    }
}

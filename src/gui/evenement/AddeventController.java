/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.evenement;

import com.jfoenix.controls.JFXTextField;
import entities.Evenement;
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
 * @author user
 */
public class AddeventController implements Initializable {

    private JFXTextField nameFld;
    

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Evenement evenement = null;
    private boolean update;
    int idevent;
    @FXML
    private JFXTextField nomFld;
    @FXML
    private JFXTextField lieuFld;
    @FXML
    private JFXTextField datedebFld;
    
    @FXML
    private JFXTextField heureFld;
    @FXML
    private JFXTextField datefinFld;
    @FXML
    private JFXTextField nbrFld;
    
    @FXML
    private JFXTextField imageFld;
    @FXML
    private JFXTextField descFld;
    @FXML
    private JFXTextField latFld;
    @FXML
    private JFXTextField longiFld;
   

    /**
     * Initializes the controller class.
     */
    int categorieID = 1;
    boolean flash=true;
    
    
 
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
   
        }
    

    @FXML
    private void save(MouseEvent event) throws SQLException {

        connection = MyDB.getInstance().getConnexion();
        
        
        String nom = nomFld.getText();
        String lieu = lieuFld.getText();
        String datedeb = datedebFld.getText();
        String heure = heureFld.getText();
        String datefin= datefinFld.getText();
        String nbr = nbrFld.getText();
        String image = imageFld.getText();
        String desc = descFld.getText();
        String lat = latFld.getText();
        String longi= longiFld.getText();
        

        if (nom.isEmpty()||lieu.isEmpty()||datedeb.isEmpty()||heure.isEmpty()||datefin.isEmpty()||nbr.isEmpty()||image.isEmpty()||desc.isEmpty()||lat.isEmpty()||longi.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez svp replir tout les champs");
            alert.showAndWait();

        } else {
            getQuery();
            insert();
            clean();

        }

    }

    @FXML
    private void clean() {
        nomFld.setText(null);
        lieuFld.setText(null);
        datedebFld.setText(null);
        heureFld.setText(null);
        datefinFld.setText(null);
        nbrFld.setText(null);
        imageFld.setText(null);
        descFld.setText(null);
        latFld.setText(null);
        longiFld.setText(null);
        
        
    }

    private void getQuery() {

        if (update == false) {
            
            query = "INSERT INTO `evenement` (`nomeven`, `lieuevent`, `datevent`, `heurevent`,`datefin`, `nbrplace`, `image`, `description`, `lat`, `longi`) VALUES (?,?,?,?,?,?,?,?,?,?)";

        }else{
            query = "UPDATE `evenement` SET "
                    + "`nomeven`=?,"
                    + "`lieuevent`=?,"
                    + "`datevent`=?,"
                    + "`heurevent`=?,"
                    + "`datefin`=?,"
                    + "`nbrplace`=?,"
                    + "`image`=?,"
                    + "`description`=?,"
                    + "`lat`=?,"
                    + "`longi`=? WHERE id = '"+idevent+"'";
        }

    }

    private void insert() throws SQLException {

        try {
            
            
            preparedStatement = connection.prepareStatement(query);
            
             
            
            preparedStatement.setString(1, nomFld.getText());
            preparedStatement.setString(2, lieuFld.getText());
            preparedStatement.setString(3,datedebFld.getText());
            preparedStatement.setString(4, heureFld.getText());
             preparedStatement.setString(5,datefinFld.getText());
            preparedStatement.setInt(6, Integer.parseInt(nbrFld.getText()));
            preparedStatement.setString(7,imageFld.getText());
            preparedStatement.setString(8,(descFld.getText()));
            preparedStatement.setString(9,(latFld.getText()));
            preparedStatement.setString(10,(longiFld.getText()));
           
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AddeventController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void setTextField(int id, String nom, String lieu, String datedeb, String heure, String datefin, int nombre, String imagee, String desc, String lat, String longi) {

        idevent= id;
        nomFld.setText(nom);
        lieuFld.setText(lieu);
        datedebFld.setText(datedeb);
        heureFld.setText(heure);
        datefinFld.setText(datefin);
        nbrFld.setText(Integer.toString(nombre));
        imageFld.setText(imagee);
        descFld.setText(desc);
        latFld.setText(lat);
        longiFld.setText(longi);
        
        
        

    }

    void setUpdate(boolean b) {
        this.update = b;

    }

}


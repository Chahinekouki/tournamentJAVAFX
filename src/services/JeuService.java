/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import utils.MyDB;
import entites.Jeu;
import entites.Tournoi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author Chahine kouki
 */
public class JeuService {
    Connection connexion;
    Statement stm;

    public JeuService() {
        connexion = MyDB.getInstance().getConnexion();
    }
    public List<Jeu> afficherJeu(){
ObservableList<Jeu> jeuxlist = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM jeu";
            Statement st = connexion.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Jeu j = new Jeu();
                j.setId(rs.getInt(1));
                j.setNom(rs.getString("nom"));
                jeuxlist.add(j);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());     
        }
    System.out.println(jeuxlist);
      return jeuxlist;
       

    }
     public List<String> afficherJeuNom() {
        List<String> jeuxNom = new ArrayList<>();
         try {
        String req = "select nom from jeu";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            jeuxNom.add(rst.getString("nom"));
        }
         } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return jeuxNom;
    }
     public void ajouterJeu(Jeu j) throws ParseException{
         try{
      String req = "INSERT INTO `jeu` (`nom`) VALUES ( '"
                + j.getNom() + "')";
        
      
        stm = connexion.createStatement();
        stm.executeUpdate(req);
        JOptionPane.showMessageDialog(null, "ajout avec succes");
} catch(SQLException ex){
    JOptionPane.showMessageDialog(null, "error");
}
    }
        // UPDATE
    public void majJeu(Jeu j) {

        try {  

            String requete = "UPDATE `jeu` set nom =? WHERE id=?";
            PreparedStatement pst = MyDB.getInstance().getConnexion().prepareStatement(requete);

            pst.setString(1, j.getNom());
            pst.setInt(2, j.getId());
            pst.executeUpdate();

            System.out.println("Jeu modifié");
            JOptionPane.showMessageDialog(null, "update");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "error");
        }

    }
      // DELETE
    public void supprimerJeu(int id) {

        try { 
//            String requete1 = "DELETE  FROM equipe where tournoi_id="+id;
            String requete2 = "DELETE  FROM jeu where id="+id;
//            PreparedStatement st = MyDB.getInstance().getConnexion().prepareStatement(requete1);
//            st.executeUpdate();
            PreparedStatement st2 = MyDB.getInstance().getConnexion().prepareStatement(requete2);
            st2.executeUpdate();
            System.out.println("jeu supprimer");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    
}
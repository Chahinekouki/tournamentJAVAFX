/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;



import utils.MyDB;
import entites.Equipe;
import entites.Tournoi;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;



/**
 *
 * @author Chahine kouki
 */
public class TournoiService {

    Connection connexion;
    Statement stm;
    ArrayList<String> joueursList = new ArrayList<>();
    Equipe e;

    public TournoiService() {
        connexion = MyDB.getInstance().getConnexion();
    }


    public void ajoutert(Tournoi t,String time1, String time2) throws SQLException, ParseException {
       
    System.out.println("java.sql.Timestamp time: " + time1+" "+ time2+ t.getTime());
    
       java.sql.Timestamp sqlTS = java.sql.Timestamp.valueOf(t.getTime()+" "+time1+":"+time2+":00.888");
       
        System.out.println("java.sql.Timestamp time: " + sqlTS);
        
        
        String reqjeu= "SELECT id FROM jeu WHERE nom LIKE '"+t.getJeu()+"'";
        System.out.println(reqjeu);
        stm=connexion.createStatement();
        ResultSet rst = stm.executeQuery(reqjeu);
        int id = 0;
        while (rst.next()) {
        id = rst.getInt("id");
        System.out.println(id);
        }
        
        String req = "INSERT INTO `tournoi` (`nom`, `nbr_equipes`,`nbr_joueur_eq`,`prix`,`discord_channel`,`time`,`jeu_id`,`organisateur_id`) VALUES ( '"
                + t.getNom() + "', '" + t.getNbr_equipes() + "','" + t.getNbr_joueur_eq() + "','" + t.getPrix()+ "','" + t.getDiscord_channel()
                + "','" + sqlTS + "', '" + id + "','" + 2 + "')";
        
      
        stm = connexion.createStatement();
        stm.executeUpdate(req);

        // retourner l'ID de tournoi 
              
        String reqtour= "SELECT id FROM tournoi WHERE nom LIKE '"+t.getNom()+"'";
        System.out.println(reqtour);
        stm=connexion.createStatement();
        ResultSet rst1 = stm.executeQuery(reqtour);
        int idt = 0;
        while (rst1.next()) {
         idt = rst1.getInt("id");
        
        }
        
            for (int i =1 ; i <= t.getNbr_equipes();i++){
              String joueurs ="";
            for (int j =1 ; j <=t.getNbr_joueur_eq(); j++ ){
                joueurs = joueurs+"vide-";
           }
           String reqeq = "INSERT INTO `equipe` ( `tournoi_id`,`label`,`joueurs`) VALUES ( '"+ idt+ "','" + "equipe"+i + "', '"+ joueurs+ "')";
           
             stm = connexion.createStatement();
            stm.executeUpdate(reqeq);        }    
       
    JOptionPane.showMessageDialog(null, "succees d'ajout");
        
        
         

    }


    public List<Tournoi> affichertournoi() throws SQLException {
        List<Tournoi> tournois = new ArrayList<>();
        
        String req = "select * from tournoi";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Tournoi t = new Tournoi(rst.getInt("id"),//or rst.getInt(1)
                    rst.getString("nom"),
                    rst.getInt("nbr_equipes"),
                    rst.getInt("nbr_joueur_eq"),
                    rst.getFloat("prix"),
                    rst.getString("image"),
                    rst.getString("discord_channel"),
                    rst.getString("time"));
                  
                    

            tournois.add(t);
        }
        return tournois;
    }
    public List<Tournoi> affichermestournoi(int id) throws SQLException {
        List<Tournoi> tournois = new ArrayList<>();
        
        String req = "select * from tournoi where organisateur_id="+id;
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Tournoi t = new Tournoi(rst.getInt("id"),//or rst.getInt(1)
                    rst.getString("nom"),
                    rst.getInt("nbr_equipes"),
                    rst.getInt("nbr_joueur_eq"),
                    rst.getFloat("prix"),
                    rst.getString("image"),
                    rst.getString("discord_channel"),
                    rst.getString("time"));
                  
                    

            tournois.add(t);
        }
        return tournois;
    }
    
    
      // UPDATE
    public void majTournoi(Tournoi t) {

        try {  
            System.out.println(t.getNom());
            System.out.println(t.getId());
           
            String requete = "UPDATE `tournoi` set nom =?,prix =?,discord_channel =? WHERE id=?";
            PreparedStatement pst = MyDB.getInstance().getConnexion().prepareStatement(requete);

            pst.setString(1, t.getNom());
            pst.setFloat(2, t.getPrix());
            pst.setString(3, t.getDiscord_channel());
            pst.setInt(4, t.getId());
          
            pst.executeUpdate();

            System.out.println("Tournoi modifié");
JOptionPane.showMessageDialog(null, "update");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "error");
        }

    }
    
    
     // DELETE
    public void supprimerTournoi(int id) {

        try { 
            String requete1 = "DELETE  FROM equipe where tournoi_id="+id;
            String requete2 = "DELETE  FROM tournoi where id="+id;
            PreparedStatement st = MyDB.getInstance().getConnexion().prepareStatement(requete1);
            st.executeUpdate();
            PreparedStatement st2 = MyDB.getInstance().getConnexion().prepareStatement(requete2);
            st2.executeUpdate();
            System.out.println("tournoi supprimer");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    
    
    public List<Equipe> afficherJoueurs(int id) throws SQLException{
            List<Equipe> equipes = new ArrayList<>();


        
            
            String requete = "SELECT * FROM equipe where tournoi_id="+id;
            
            Statement st = connexion.createStatement();
            
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Equipe e = new Equipe(rs.getInt("id"),//or rst.getInt(1)
                    rs.getString("label"),
                    rs.getString("joueurs"));
                  
                    

            equipes.add(e);
            }
         
       
    System.out.println(equipes);
      return equipes;
       

    }
    
    public void inscrireJoueur(Equipe e){
           
        try {  
            System.out.println(e.getJoueurs());
            System.out.println(e.getId());
            // rechercher l'index de mot vide
//            int index = e.getJoueurs().indexOf("Vide-");
//            //remplacer vide par ACTIVE
            String test = e.getJoueurs().replaceFirst("vide", "ACTIVE");
            System.out.println(test);
            String requete = "UPDATE `equipe` set joueurs =? WHERE id=?";
            PreparedStatement pst = MyDB.getInstance().getConnexion().prepareStatement(requete);

            pst.setString(1, test);
            pst.setInt(2, e.getId());
          
            pst.executeUpdate();

            System.out.println("Joueur inscrit");
JOptionPane.showMessageDialog(null, "Joueur inscrit");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "error");
        }    
    
}
     public void desinscrireJoueur(Equipe e){
           
        try {  
            System.out.println(e.getJoueurs());
            System.out.println(e.getId());
            // rechercher l'index de mot vide
//            int index = e.getJoueurs().indexOf("Vide-");
//            //remplacer vide par ACTIVE
            String test = e.getJoueurs().replaceFirst("ACTIVE", "vide");
            System.out.println(test);
            String requete = "UPDATE `equipe` set joueurs =? WHERE id=?";
            PreparedStatement pst = MyDB.getInstance().getConnexion().prepareStatement(requete);

            pst.setString(1, test);
            pst.setInt(2, e.getId());
          
            pst.executeUpdate();

            System.out.println("Joueur inscrit");
JOptionPane.showMessageDialog(null, "Joueur inscrit");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "error");
        }    
    
}
}



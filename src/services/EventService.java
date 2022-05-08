/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Evenement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDB;

/**
 *
 * @author user
 */

public class EventService implements IEvent<Evenement> {

    Connection connexion;
    Statement stm;

    public EventService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    public void ajouterevent(Evenement e) throws SQLException {
        String req = "INSERT INTO `evenement` (`id`,`nomeven`, `lieuevent`, `datevent`, `heurevent`,`datefin`, `nbrplace`, `image`, `description`, `lat`, `longi`) VALUES (NULL+'"
                + e.getNom() + "', '" + e.getLieu()+ "', '" + e.getDatedeb()+ "', '" + e.getHeure()+ "','" + e.getDatefin()+ "' '" + e.getNombre()+ "', '" + e.getImagee()+ "', '" + e.getDesc()+ "', '" + e.getLat()+ "', '" + e.getLongi()+ "') ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);

    }

    public List<Evenement> afficherevent() throws SQLException {
        List<Evenement> evenements= new ArrayList<>();
        String req = "select * from evenement";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
        
         

        while (rst.next()) {
            Evenement e = new Evenement(//or rst.getInt(1)
                    rst.getString("nomeven"),
                    rst.getString("lieuevent"),
                    rst.getString("datevent"),
                    rst.getString("heurevent"),
                    rst.getString("datefin"),
                    rst.getInt("nbrplace"),
                    rst.getString("description"),
                    rst.getString("image"),
                    rst.getString("longi"),
                    rst.getString("lat"));
            evenements.add(e);
        }
        return evenements;
    }
    
    public void DeleteEvenement(int id) {
        try
        {
       Statement stm = connexion.createStatement();
       String req = "DELETE FROM `evenement` WHERE id="+id+";";
       stm.executeUpdate(req);
       System.out.println("Suppression effectue avec succes");
        }catch(SQLException ex){
            System.out.println("Erreur Suppresions");
             Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    
     
    public void UpdateEvenement(Evenement e,int id) {
        try {
            System.out.println(e.getDatedeb());
            Date date=Date.valueOf(e.getDatedeb().toString());
            Date date2=Date.valueOf(e.getDatefin());
           System.out.println(date);
           System.out.println(date2);
            Statement stm = connexion.createStatement();
             String req = "UPDATE `evenement` SET `nomeven`='"+e.getNom()+"',`lieuevent`='"+e.getLieu()+"',`datevent`='"+e.getDatedeb()+"',`heurevent`='"+e.getHeure()+"',`datefin`='"+e.getDatefin()+"',`nbrplace`='"+e.getNombre()+"' ,`description`='"+e.getDesc()+"' ,`image`='"+e.getImagee()+"' ,`longi`='"+e.getLongi()+"' ,`lat`='"+e.getLat()+"'WHERE id="+id+"";
            stm.executeUpdate(req);
             System.out.println("Modification effectué avec succés");
            
        } catch (SQLException ex) {
            System.out.println("probleme modification");
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}
}

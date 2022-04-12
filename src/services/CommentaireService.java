/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.ResultSet;
import entities.Commentaire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author Aymen Laroussi
 */
public class CommentaireService implements ICommentaire<Commentaire> {

    Connection connexion;
    Statement stm;

    public CommentaireService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    @Override
    public void ajoutCommentaire(Commentaire c) throws SQLException {
        
        String req = "INSERT INTO `commentaires` (`user_id`,`produit_id`, `message`,`date` ) VALUES ( '"
                + c.getUser_id()+ "', '" + c.getProduit_id()+ "', '"+ c.getMessage()+ "', '"+ c.getDate()+ "') ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);

    }

    @Override
    public List<Commentaire> afficheCommentaire() throws SQLException {
        List<Commentaire> coment = new ArrayList<>();
        String req = "select * from commentaires";
        stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Commentaire c = new Commentaire(rst.getInt("id"),
                    rst.getInt("user_id"),
                    rst.getInt("produit_id"),
                    rst.getString("message"),
                    rst.getDate("date"));
            coment.add(c);
        }
        return coment;
    }

    public void majCommentaire(Commentaire c) {

        try {  

            String requete = "UPDATE commentaires set user_id =?,produit_id =? ,message =?,date =? WHERE id=?";
            PreparedStatement pst = MyDB.getInstance().getConnexion().prepareStatement(requete);

            pst.setInt(1, c.getUser_id());
            pst.setInt(2, c.getProduit_id());
            pst.setString(3, c.getMessage());
            pst.setDate(2, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            pst.setInt(5, c.getId());
            pst.executeUpdate();

            System.out.println("Produits modifie");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }


}



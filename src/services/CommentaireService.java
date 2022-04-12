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


}



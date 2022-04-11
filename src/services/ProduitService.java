/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Produit;
import java.sql.ResultSet;
import entities.Categories;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author Aymen Laroussi
 */
public class ProduitService implements IProduits<Produit> {

    Connection connexion;
    Statement stm;

    public ProduitService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    @Override
    public void ajoutProduit(Produit p) throws SQLException {
        String req = "INSERT INTO `produits` (`categories_id`,`titre`, `description`,`promo`,`stock`,`flash`,`image`,`ref`,`longdescription`, `prix` ) VALUES ( '"
                + p.getCategorie()+ "', '" + p.getTitre()+ "', '"+ p.getDescription()+ "', '"+ p.getPromo()+ "', '" +p.getStock()+ "', '" +p.getFlash()+ "', '" +p.getImage()+ "', '" +p.getRef()+ "', '" +p.getLongdescription()+ "', '" +p.getPrix()+ "') ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);

    }


}



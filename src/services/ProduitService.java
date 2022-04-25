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


    @Override
    public List<Produit> afficheProduit() throws SQLException {
        List<Produit> produits = new ArrayList<>();
        String req = "select * from produits";
        stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Produit p = new Produit(rst.getInt("id"),//or rst.getInt(1)
                    rst.getInt("categories_id"),
                    rst.getString("titre"),
                    rst.getString("description"),
                    rst.getFloat("promo"),
                    rst.getFloat("stock"),
                    rst.getBoolean("flash"),
                    rst.getString("image"),
                    rst.getString("ref"),
                    rst.getString("longdescription"),
                    rst.getFloat("prix"));
            produits.add(p);
        }
        return produits;
    }

    // UPDATE
    public void majProduit(Produit p) {

        try {  

            String requete = "UPDATE produits set categories_id =?,titre =? ,description =?,promo =?,stock =?,flash =?,image =?,ref =?,longdescription =?,prix =? WHERE id=?";
            PreparedStatement pst = MyDB.getInstance().getConnexion().prepareStatement(requete);

            pst.setInt(1, p.getCategorie());
            pst.setString(2, p.getTitre());
            pst.setString(3, p.getDescription());
            pst.setFloat(4, p.getPromo());
            pst.setFloat(5, p.getStock());
            pst.setBoolean(6, p.getFlash());
            pst.setString(7, p.getImage());
            pst.setString(8, p.getRef());
            pst.setString(9, p.getLongdescription());
            pst.setFloat(10, p.getPrix());
            pst.setInt(11, p.getId());
            pst.executeUpdate();

            System.out.println("Produits modifie");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 

    }

    // DELETE
    public void supprimerPrdouit(int id) {

        try { 
            String requete = "DELETE  FROM produits where id="+id;
            PreparedStatement st = MyDB.getInstance().getConnexion().prepareStatement(requete);
            st.executeUpdate();
            System.out.println("produit supprimer");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    
     

}



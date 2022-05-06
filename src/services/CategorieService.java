package services;

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
public class CategorieService implements ICategories<Categories> {

    Connection connexion;
    Statement stm;

    public CategorieService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    @Override
    public void ajoutCategorie(Categories p) throws SQLException {
        String req = "INSERT INTO `categories` (`nom` ) VALUES ( '"
                + p.getNom()+ "') ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);

    }

    @Override
    public List<Categories> afficheCategorie() throws SQLException {
        List<Categories> categorie = new ArrayList<>();
        String req = "select * from categories";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Categories p = new Categories(rst.getInt("id"),//or rst.getInt(1)
                    rst.getString("nom"));
            categorie.add(p);
        }
        return categorie;
    }

    public void majCategorie(Categories c) {

        try {  

            String requete = "UPDATE categories set nom =? WHERE id=?";
            PreparedStatement pst = MyDB.getInstance().getConnexion().prepareStatement(requete);

            pst.setString(1, c.getNom());
            pst.setInt(2, c.getId());
            pst.executeUpdate();

            System.out.println("Categorie modifie");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    
    
   
    
    
}

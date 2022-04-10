/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    public List<Categories> afficheCategorie() throws SQLException {
        List<Categories> categorie = new ArrayList<>();
        String req = "select * from categories";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Categories p = new Categories(rst.getInt("id"),
                    rst.getString("nom"));
            categorie.add(p);
        }
        return categorie;
    }
    
    
   
    
    
}

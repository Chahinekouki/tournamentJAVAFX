/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Categories;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.CategorieService;



/**
 *
 * @author Aymen Laroussi
 */
public class test {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        // DECLATRATION
        Categories categorie2 = new Categories();
        CategorieService sp = new CategorieService();
        
        // RETRIVE DATA FROM DATABASE
        
        System.out.println("********* RETREVE Categorie ************");
         try {
            System.out.println(sp.afficheCategorie());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Categories;
import entities.Produit;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.PersonneService;
import services.CategorieService;
import services.ProduitService;



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
        
        
        // CRUD CATEGORIE
        try {
            sp.ajoutCategorie(c);                                       // CREATE NEW CATEGORIE
            System.out.println(sp.afficheCategorie());                 // RETRIVE DATA FROM DATABASE
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        // CRUD PRODUIT
        Produit p = new Produit(1,"ghof", "ghof",10,10,1,"test.png","#ghof","ghof",10);
        Produit p1 = new Produit(109,1,"ghof22222", "ghof",10,10,1,"test.png","#ghof","ghof",10);
        ProduitService sp1 = new ProduitService();
        CommentaireService coment = new CommentaireService();
        
        try {
             sp1.ajoutProduit(p);                                      // CREATE NEW PRODUIT
             System.out.println(p);                                   // RETRIVE NEW PRODUIT
             System.out.println("Produit ajouté avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
             System.out.println("erreur");
        }

        System.out.println(">> UPDATE PRODUIT ");
            sp1.majProduit(p1);                                       // UPDATE PRODUIT
            System.out.println("Produit modifié avec succes"); 


        
        
        
        
    }
    
}

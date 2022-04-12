/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Categories;
import entities.Produit;
import entities.Commentaire;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.PersonneService;
import services.CategorieService;
import services.ProduitService;
import services.CommentaireService;



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
        System.out.println(">> CREATE CATEGORIE ");
            sp.ajoutCategorie(c);                                       // CREATE NEW CATEGORIE
            System.out.println(sp.afficheCategorie());                 // RETRIVE DATA FROM DATABASE
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


        // CRUD PRODUIT
        Produit p = new Produit(1,"titre", "description",10,10,1,"image.png","#ref","description",10);
        Produit p1 = new Produit(109,1,"may", "virgo",10,10,1,"image.png","#ghof","ghof",10);
        ProduitService sp1 = new ProduitService();
        
        try {
        System.out.println(">> CREATE PRODUIT ");
             sp1.ajoutProduit(p);                                      // CREATE NEW PRODUIT
             System.out.println(p);                                   
             System.out.println("Produit ajouté avec succes");
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
             System.out.println("erreur");
        }

        try {
        System.out.println(">> RETRIVE PRODUIT ");
            System.out.println(sp1.afficheProduit());                 // RETRIVE PRODUITS
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

            System.out.println(">> UPDATE PRODUIT ");
            sp1.majProduit(p1);                                       // UPDATE PRODUIT
            System.out.println("Produit modifié avec succes"); 

        System.out.println(">> DELETE PRODUIT");
            int id1=1;
            sp1.supprimerPrdouit(id);                                // DELETE PRODUIT
            System.out.println("Produit supprimé avec succes");


        // CRUD COMMENTAIRE
        CommentaireService coment = new CommentaireService();
        Date date = new Date();
        Timestamp date1 = new Timestamp(date.getTime()); // get current date
        Commentaire c = new Commentaire(1,1,"may",date1);
        System.out.println(">> CREATE COMMENTAIRE ");
            coment.ajoutCommentaire(c);                             // CREATE NEW COMMENTAIRE
        
        
        
        
        
    }
    
}

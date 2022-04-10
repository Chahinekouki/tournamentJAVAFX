/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author macbook
 */
public class Categories {
    
  private int id;
  private String nom;

    public Categories(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Categories(String nom) {
        this.nom = nom;
    }

    public Categories() {
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Categories{" + "id=" + id + ", nom=" + nom + '}';
    }
  
  
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author user
 */
public class Sponsor {
    private int id;
    private String nom;
    private int num;
    private float budget;
    private String image;

    public Sponsor(int id, String nom, int num, float budget, String image) {
        this.id = id;
        this.nom = nom;
        this.num = num;
        this.budget = budget;
        this.image = image;
    }

    public Sponsor(String nom, int num, float budget, String image) {
        this.nom = nom;
        this.num = num;
        this.budget = budget;
        this.image = image;
    }

    

    public Sponsor(String image) {
        this.image = image;
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

   
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    

    @Override
    public String toString() {
        return "Sponsor{" + "id=" + id + ", nom=" + nom + ", num=" + num + ", budget=" + budget + ", image=" + image + '}';
    }

    
}

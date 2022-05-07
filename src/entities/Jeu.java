/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javafx.scene.control.Button;

/**
 *
 * @author Chahine kouki
 */
public class Jeu {
    private int id ;
    private String nom;
    private Button update;
    private Button delete;

    public Jeu(int id, String nom) {
        this.id = id;
        this.nom = nom;
//        this.delete = new Button("delete");
//        this.delete.setStyle("-fx-background-color: #d01f1f; ");
//        this.update = new Button("update");
//        this.update.setStyle("-fx-background-color: #41c12d; ");
    }
    public Jeu() {
        
    }

    public Jeu(String nom) {
        this.nom = nom;
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
        return "Jeu{" + "id=" + id + ", nom=" + nom + '}';
    }

    public Button getUpdate() {
        return update;
    }

    public void setUpdate(Button update) {
        this.update = update;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }
    
}

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
public class Tournoi {

    private int id;
    private String nom;
    private int nbr_equipes;
    private int nbr_joueur_eq;
    private float prix;
    private String image;
    private String discord_channel;
    private String time;
    private String timeEnd ;
    private String jeu;
    private Button update;
    private Button delete;
    private String color ;
   

    public Tournoi(int id, String nom, int nbr_equipes, int nbr_joueur_eq, float prix, String image, String discord_channel, String time) {
        this.id = id;
        this.nom = nom;
        this.nbr_equipes = nbr_equipes;
        this.nbr_joueur_eq = nbr_joueur_eq;
        this.prix = prix;
        this.image = image;
        this.discord_channel = discord_channel;
        this.time = time;
        this.delete = new Button("delete");
        this.delete.setStyle("-fx-background-color: #d01f1f; ");
        this.update = new Button("update");
        this.update.setStyle("-fx-background-color: #41c12d; ");
        
    }


//    public Tournoi(String nom, int nbr_equipes, int nbr_joueur_eq, float prix, String image, String discord_channel, String time) {
//        this.nom = nom;
//        this.nbr_equipes = nbr_equipes;
//        this.nbr_joueur_eq = nbr_joueur_eq;
//        this.prix = prix;
//        this.image = image;
//        this.discord_channel = discord_channel;
//        this.time = time;
//        
//    }

    public Tournoi(String nom, int nbr_equipes, int nbr_joueur_eq, float prix, String discord_channel, String time, String jeu) {
        this.nom = nom;
        this.nbr_equipes = nbr_equipes;
        this.nbr_joueur_eq = nbr_joueur_eq;
        this.prix = prix;
        this.image = image;
        this.discord_channel = discord_channel;
        this.time = time;
        this.jeu = jeu;
    }

    public Tournoi() {
    }

    public String getJeu() {
        return jeu;
    }

    public void setJeu(String jeu) {
        this.jeu = jeu;
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

    public int getNbr_equipes() {
        return nbr_equipes;
    }

    public void setNbr_equipes(int nbr_equipes) {
        this.nbr_equipes = nbr_equipes;
    }

    public int getNbr_joueur_eq() {
        return nbr_joueur_eq;
    }

    public void setNbr_joueur_eq(int nbr_joueur_eq) {
        this.nbr_joueur_eq = nbr_joueur_eq;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDiscord_channel() {
        return discord_channel;
    }

    public void setDiscord_channel(String discord_channel) {
        this.discord_channel = discord_channel;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }

    public Button getUpdate() {
        return update;
    }

    public void setUpdate(Button update) {
        this.update = update;
    }

    @Override
    public String toString() {
        return "Tournoi{" + "id=" + id + ", nom=" + nom + ", nbr_equipes=" + nbr_equipes + ", nbr_joueur_eq=" + nbr_joueur_eq + ", prix=" + prix + ", image=" + image + ", discord_channel=" + discord_channel + ", time=" + time + ", timeEnd=" + timeEnd + ", jeu=" + jeu + ", update=" + update + ", delete=" + delete + ", color=" + color + '}';
    }


}

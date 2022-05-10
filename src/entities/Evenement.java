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
public class Evenement {
    private int id;
    private String nom;
    private String lieu;
    private String datedeb;
    private String heure;
    private String datefin;
    private int nombre;
    private String imagee;
    private String desc;
    private String lat;
    private String longi;

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

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDatedeb() {
        return datedeb;
    }

    public void setDatedeb(String datedeb) {
        this.datedeb = datedeb;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public String getImagee() {
        return imagee;
    }

    public void setImagee(String imagee) {
        this.imagee = imagee;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public Evenement(int id, String nom, String lieu, String datedeb, String heure, String datefin, int nombre, String imagee, String desc, String lat, String longi) {
        this.id = id;
        this.nom = nom;
        this.lieu = lieu;
        this.datedeb = datedeb;
        this.heure = heure;
        this.datefin = datefin;
        this.nombre = nombre;
        this.imagee = imagee;
        this.desc = desc;
        this.lat = lat;
        this.longi = longi;
    }

    public Evenement(String nom, String lieu, String datedeb, String heure, String datefin, int nombre, String imagee, String desc, String lat, String longi) {
        this.nom = nom;
        this.lieu = lieu;
        this.datedeb = datedeb;
        this.heure = heure;
        this.datefin = datefin;
        this.nombre = nombre;
        this.imagee = imagee;
        this.desc = desc;
        this.lat = lat;
        this.longi = longi;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", nom=" + nom + ", lieu=" + lieu + ", datedeb=" + datedeb + ", heure=" + heure + ", datefin=" + datefin + ", nombre=" + nombre + ", imagee=" + imagee + ", desc=" + desc + ", lat=" + lat + ", longi=" + longi + '}';
    }
    
    
}

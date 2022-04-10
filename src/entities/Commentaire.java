/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Aymen Laroussi
 */
public class Commentaire {

    private int id;
    private int user_id;
    private int produit_id;
    private String message;
    private Date  date;

    public Commentaire(int id, int user_id, int produit_id, String message, Date date) {
        this.id = id;
        this.user_id = user_id;
        this.produit_id = produit_id;
        this.message = message;
        this.date = date;
    }

    public Commentaire(int user_id, int produit_id, String message, Date date) {
        this.user_id = user_id;
        this.produit_id = produit_id;
        this.message = message;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getProduit_id() {
        return produit_id;
    }

    public void setProduit_id(int produit_id) {
        this.produit_id = produit_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", user_id=" + user_id + ", produit_id=" + produit_id + ", message=" + message + ", date=" + date + '}';
    }

    public Commentaire(int user_id, int produit_id, String message) {
        this.user_id = user_id;
        this.produit_id = produit_id;
        this.message = message;
    }
   
    
    

    
    
}

package entities;

import java.util.Date;

/**
 *
 * @author Aymen Laroussi
 */
public class Commentaire {

    private int id;
    private int user_id;
    private String user; 
    private int produit_id;
    private String produit;
    private String message;
    private Date  date;
    private float rating;

    public Commentaire(int id, String user, String message, Date date, float rating) {
        this.id = id;
        this.user = user;
        this.message = message;
        this.date = date;
        this.rating = rating;
    }

    public Commentaire(String user, String message, Date date, float rating) {
        this.user = user;
        this.message = message;
        this.date = date;
        this.rating = rating;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getUser() {
        return user;
    }

    public Commentaire(String user) {
        this.user = user;
    }

    public Commentaire(int id, int user_id, String user, int produit_id, String message, Date date) {
        this.id = id;
        this.user_id = user_id;
        this.user = user;
        this.produit_id = produit_id;
        this.message = message;
        this.date = date;
    }

    public Commentaire(int id, String user, String produit, String message, Date date) {
        this.id = id;
        this.user = user;
        this.produit = produit;
        this.message = message;
        this.date = date;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

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

    public Commentaire(int id, int user_id, String user, int produit_id, String produit, String message, Date date) {
        this.id = id;
        this.user_id = user_id;
        this.user = user;
        this.produit_id = produit_id;
        this.produit = produit;
        this.message = message;
        this.date = date;
    }

    public Commentaire(int user_id, String user, int produit_id, String produit, String message, Date date) {
        this.user_id = user_id;
        this.user = user;
        this.produit_id = produit_id;
        this.produit = produit;
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

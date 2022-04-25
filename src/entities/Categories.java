package entities;

/**
 *
 * @author Aymen Laroussi
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

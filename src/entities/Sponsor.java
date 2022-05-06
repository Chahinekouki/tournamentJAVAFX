package entities;

/**
 *
 * @author aicha
 */
public class Sponsor {
    private int id;
    private String nom;
    private String prenom;
    private int num;
    private float budget;
    private String image;

    public Sponsor(String nom, String prenom, int num, float budget, String image) {
        this.nom = nom;
        this.prenom = prenom;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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

    public Sponsor(int id, String nom, String prenom, int num, float budget, String image) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.num = num;
        this.budget = budget;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Sponsor{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", num=" + num + ", budget=" + budget + ", image=" + image + '}';
    }

    
}

package entities;

/**
 *
 * @author Aymen Laroussi
 */
public class Produit {
    
    private int id;
    private int categorie;
    private String nom;
    private String titre;
    private String description;
    private float promo;
    private float  stock;
    private boolean flash;
    private String image;
    private String ref;
    private String longdescription;
    private float prix;


    public Produit( float stock,int id) {
        this.id = id;
        this.stock = stock;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public Produit(int id, int categorie, String titre, String description, float promo, float stock, boolean flash, String image, String ref, String longdescription, float prix) {
        this.id = id;
        this.categorie = categorie;
        this.titre = titre;
        this.description = description;
        this.promo = promo;
        this.stock = stock;
        this.flash = flash;
        this.image = image;
        this.ref = ref;
        this.longdescription = longdescription;
        this.prix = prix;
    }

    public Produit(String titre, float prix) {
        this.titre = titre;
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", categorie=" + categorie + ", titre=" + titre + ", description=" + description + ", promo=" + promo + ", stock=" + stock + ", flash=" + flash + ", image=" + image + ", ref=" + ref + ", longdescription=" + longdescription + ", prix=" + prix + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPromo() {
        return promo;
    }

    public void setPromo(float promo) {
        this.promo = promo;
    }

    public float getStock() {
        return stock;
    }

    public void setStock(float stock) {
        this.stock = stock;
    }

    public boolean getFlash() {
        return flash;
    }

    public Produit(int id) {
        this.id = id;
    }

    public void setFlash(boolean flash) {
        this.flash = flash;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getLongdescription() {
        return longdescription;
    }

    public void setLongdescription(String longdescription) {
        this.longdescription = longdescription;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Produit(String titre, String description, float promo, float stock, boolean flash, String image, String ref, String longdescription, float prix) {
        this.titre = titre;
        this.description = description;
        this.promo = promo;
        this.stock = stock;
        this.flash = flash;
        this.image = image;
        this.ref = ref;
        this.longdescription = longdescription;
        this.prix = prix;
    }

    public Produit(int categorie, String titre, String description, float promo, float stock, boolean flash, String image, String ref, String longdescription, float prix) {
        this.categorie = categorie;
        this.titre = titre;
        this.description = description;
        this.promo = promo;
        this.stock = stock;
        this.flash = flash;
        this.image = image;
        this.ref = ref;
        this.longdescription = longdescription;
        this.prix = prix;
    }
    
    public Produit(int id, int categorie, String nom, String titre, String description, float promo, float stock, boolean flash, String image, String ref, String longdescription, float prix) {
        this.id = id;
        this.categorie = categorie;
        this.nom = nom;
        this.titre = titre;
        this.description = description;
        this.promo = promo;
        this.stock = stock;
        this.flash = flash;
        this.image = image;
        this.ref = ref;
        this.longdescription = longdescription;
        this.prix = prix;
    }
   

   

    
    
}

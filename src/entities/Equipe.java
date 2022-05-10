/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Chahine kouki
 */
public class Equipe {
    private int id ;
    private String label;
    private String joueurs;

    public Equipe(int id, String label, String joueurs) {
        this.id = id;
        this.label = label;
        this.joueurs = joueurs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(String joueurs) {
        this.joueurs = joueurs;
    }

    @Override
    public String toString() {
        return "Equipe{" + "id=" + id + ", label=" + label + ", joueurs=" + joueurs + '}';
    }
    
}

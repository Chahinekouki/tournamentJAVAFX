package services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Aymen Laroussi
 */
public interface IProduits<T> {
    
    public void ajoutProduit(T p) throws SQLException;          // Crud
    public List<T> afficheProduit() throws SQLException;       // cRud
    public void majProduit(T p) throws SQLException;          // crUd
    public void supprimerPrdouit(int id) throws SQLException;// cruD 
    
    
}

package services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Aymen Laroussi
 */
public interface ICategories<T> {
    
     public void ajoutCategorie(T p) throws SQLException;          // Crud
     public List<T> afficheCategorie() throws SQLException;       // cRud
     public void majCategorie(T p) throws SQLException;          // crUd
    
    
}

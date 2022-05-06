package services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Aymen Laroussi
 */
public interface ICommentaire<T> {
    
    public void ajoutCommentaire(T c) throws SQLException;          // Crud
    public List<T> afficheCommentaire() throws SQLException;       // cRud
    public void majCommentaire(T p) throws SQLException;          // crUd
    public void supprimerCommentaire(int id) throws SQLException;// cruD
    
}

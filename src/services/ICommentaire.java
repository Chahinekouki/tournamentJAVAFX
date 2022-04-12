/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Aymen Laroussi
 */
public interface ICommentaire<T> {
    
    public void ajoutCommentaire(T c) throws SQLException;
    public List<T> afficheCommentaire() throws SQLException;
    public void majCommentaire(T p) throws SQLException;
    public void supprimerCommentaire(int id) throws SQLException; 
    
}

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
public interface ICategories<T> {
    
     public void ajoutCategorie(T p) throws SQLException;          // Crud
     public List<T> afficheCategorie() throws SQLException;       // cRud
     public void majCategorie(T p) throws SQLException;          // crUd
    
    
}

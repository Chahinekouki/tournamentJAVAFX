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
public interface IProduits<T> {
    
    public void ajoutProduit(T p) throws SQLException;          // Crud
    public List<T> afficheProduit() throws SQLException;       // cRud
    public void majProduit(T p) throws SQLException;          // crUd
    
    
}

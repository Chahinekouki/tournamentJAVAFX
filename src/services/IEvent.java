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
 * @author user
 */
public interface IEvent<T> {
    
    public void ajouterevent(T p) throws SQLException;
    public List<T> afficherevent() throws SQLException;
      public void DeleteEvenement(int id);
       public void UpdateEvenement(T e, int id);
    
    
}

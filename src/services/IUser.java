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
 * @author aymen
 */
public interface IUser<T> {
    
    public void ajouterUser(T p) throws SQLException;
    public List<T> afficherUser() throws SQLException;
}
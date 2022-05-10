/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.User;
import static gui.user.LoginFXMLController.logged;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

import utils.MyDB;

/**
 *
 * @author aymen
 */
public class UserService implements IUser<User> {

    Connection connexion;
    Statement stm;

    public UserService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    @Override
    public List<User> afficherUser() throws SQLException {
        List<User> users = new ArrayList<>();
        String req = "select * from user";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            User p = new User(rst.getInt("id"),//or rst.getInt(1)
                    rst.getString("username"),
                    rst.getString("email"),
                    rst.getString("password"));
            users.add(p);
        }
        return users;
    }

    @Override
    public void ajouterUser(User p) throws SQLException {
        String req = "INSERT INTO `user` (`username`, `email`,`password`, `roles`) VALUES ( '"
                + p.getUsername()+ "', '" + p.getEmail()+ "', '" + p.getPassword()+ "', 'ADMIN') ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }
    
    public void modifier(String email, User t) {
        try {
            PreparedStatement st;
            st=connexion.prepareStatement("UPDATE `user` SET `password`=? WHERE email=?");
            st.setString(1, t.getPassword());
            st.setString(2, email);
            if (st.executeUpdate()==1){
            System.out.println("user modifier avec success");
            }else {
                // System.out.println("L'utilisateur n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public List<User> findByEmail(String email){
         List<User> users = afficher();
         List<User> resultat=users.stream().filter(user->email.equals(user.getEmail())).collect(Collectors.toList());
         if(resultat.isEmpty()){
            // System.out.println("L'utilisateur n'existe pas");
        }else{
            // System.out.println("L'utilisateur existe");
        }
         return resultat;
     }
    
    public List<User> afficher() {
        List<User> lu=new ArrayList<>();
        try {
            Statement st=connexion.createStatement();
            String query="select * from user";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                User u =new User();
                u.setEmail(rs.getString("email"));
                lu.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }
}

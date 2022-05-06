package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Aymen Laroussi
 */
public class MyDB {
    
    final String url ="jdbc:mysql://localhost:3306/pi";
    final String login ="root";
    final String pwd="";
    private static MyDB instance;
    Connection connexion;
    
    
    private MyDB(){
        
        try {
            connexion =  DriverManager.getConnection(url, login, pwd);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public static MyDB getInstance(){
    if (instance == null)
        instance = new MyDB();
    return instance;
    }

    public Connection getConnexion() {
        return connexion;
    }
    
    
    
    
    
}

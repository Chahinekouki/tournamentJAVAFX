/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Set;

/**
 *
 * @author mind
 */
public class SessionUser {
    int id;
    String username;
    String password;
    String email;
    String roles;
    
    public static SessionUser instance = null;
    public static SessionUser getInstance() {
        if (instance == null) {
            instance = new SessionUser();
        }
        return instance;
    }
    
    public SessionUser() {
       //
    }

    public void SetSessionUser(int id, String username, String email, String roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getRoles() {
        return roles;
    }
    
    public void setRoles(String roles) {
        this.roles = roles;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public SessionUser(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
    
    public SessionUser(int id, String username) {
        this.id = id;
        this.username = username;
    }
}
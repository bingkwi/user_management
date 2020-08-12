package com.example.restfulweb.models;

import java.sql.Date;
import java.sql.Timestamp;

public class User {
    
    private int id;
    private String username;
    private String password;
    private String salt;
    private Timestamp created;

    public User(String username, String password, String salt, Timestamp created){
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.created = created;
    }
    
    public User(int id, String username, String password, String salt, Timestamp created){
        this.id = id;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.created = created;
    }

    public int getId(){
        return id;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getSalt(){
        return salt;
    }

    public Timestamp getCreated(){
        return created;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setSalt(String salt){
        this.salt = salt;
    }

    public void setCreated(Timestamp created){
        this.created = created;
    }

    @Override
    public String toString(){
        return "User:<" + id + ", username = " + username + ", created at = " + created + ">";
    }  
}
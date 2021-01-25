package com.ka.krishnaaqua.SessionManagement;

public class User {

    int id;
    String name, email, address, mobile;


    public User ( int id , String s ) {
        this.id    = id;
        this.email = email;
    }

    public int getId () {
        return id;
    }

    public void setId ( int id ) {
        this.id = id;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail ( String email ) {
        this.email = email;
    }


}

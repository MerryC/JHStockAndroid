package com.example.jimshoney;

public class Login {
    private String username,pass;

    public Login(String username, String pass){
        this.username = username;
        this.pass = pass;
    }

    public String getUsername() {
        return username;
    }

    public String getPass() {
        return pass;
    }
}

package com.javabeginnerstutorial.bootdemo.model;

import java.io.Serializable;

public class TokenRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150808L;
    private String userName;
    private String password;

    public TokenRequest() {
    }

    public TokenRequest(String userName, String password) {
        this.setUsername(userName);
        this.setPassword(password);
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

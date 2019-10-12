package com.javabeginnerstutorial.bootdemo.service;


import com.javabeginnerstutorial.bootdemo.model.User;

public interface UserService {

    public User findUserByEmail(String email) ;
    public User findUserByName(String name) ;
    public User saveUser(User user);
}

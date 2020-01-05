package com.javabeginnerstutorial.bootdemo.service;


import com.javabeginnerstutorial.bootdemo.model.UserInfo;

public interface UserService {

    public UserInfo findUserByEmail(String email) ;
    public UserInfo findUserByName(String name) ;
    public UserInfo saveUser(UserInfo userInfo);
}

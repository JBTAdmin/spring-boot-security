package com.javabeginnerstutorial.bootdemo.repository;


import com.javabeginnerstutorial.bootdemo.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer> {
    public UserInfo findByEmail(String email);
    public UserInfo findByName (String name);
}

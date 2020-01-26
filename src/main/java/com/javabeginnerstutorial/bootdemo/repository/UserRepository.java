package com.javabeginnerstutorial.bootdemo.repository;


import com.javabeginnerstutorial.bootdemo.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer> {
    public Optional<UserInfo> findByEmail(String email);
    public Optional<UserInfo> findByName (String name);
}

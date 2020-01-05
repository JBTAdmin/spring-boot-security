package com.javabeginnerstutorial.bootdemo.service;

import com.javabeginnerstutorial.bootdemo.model.Role;
import com.javabeginnerstutorial.bootdemo.model.UserInfo;
import com.javabeginnerstutorial.bootdemo.repository.RoleRepository;
import com.javabeginnerstutorial.bootdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserInfo findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserInfo findUserByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public UserInfo saveUser(UserInfo userInfo) {
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userInfo.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        userInfo.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(userInfo);
    }
}

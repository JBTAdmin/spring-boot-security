package com.javabeginnerstutorial.bootdemo.service;

import com.javabeginnerstutorial.bootdemo.model.Role;
import com.javabeginnerstutorial.bootdemo.model.UserInfo;
import com.javabeginnerstutorial.bootdemo.repository.RoleRepository;
import com.javabeginnerstutorial.bootdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service("userService")
public class UserServiceImpl implements UserDetailsService, UserService {

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
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Mail Id " + email + "not found"));
    }

    @Override
    public UserInfo findUserByName(String username) {
        return userRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found"));
    }

    @Override
    public UserInfo saveUser(UserInfo userInfo) {
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userInfo.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        userInfo.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(userInfo);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found"));
        return new User(userInfo.getName(), userInfo.getPassword(), getAuthority(userInfo.getRoles().stream().collect(Collectors.toList())));
    }

    private List getAuthority(List<Role> userRole) {
        List<SimpleGrantedAuthority> grantedAuthorities = userRole.stream()
        .map(s -> new SimpleGrantedAuthority("ROLE_"+s.getRole()))
        .collect(Collectors.toList());
        return grantedAuthorities;
    }
}

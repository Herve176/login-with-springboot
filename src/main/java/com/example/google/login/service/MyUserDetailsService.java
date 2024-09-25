package com.example.google.login.service;

import com.example.google.login.model.User;
import com.example.google.login.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.google.login.model.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;




@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;


    @Override
    public UserDetails loadUserByUsername(String useremail) throws UsernameNotFoundException {

        User user= repo.findByUseremail(useremail);

        if (user==null) {
            System.out.println("User 404");
            throw new UsernameNotFoundException("User 404");
        }
        return new UserPrincipal(user);
    }

}
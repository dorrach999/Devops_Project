package com.api.gestiontaches.security.service;

import com.api.gestiontaches.entities.Employee;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{

    IAccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee user= accountService.loadUserByUserName(username);
        List<GrantedAuthority> authorityList=new ArrayList<>();
        user.getRoles().forEach(e->authorityList.add(new SimpleGrantedAuthority(e.getRole())));

        return new User(user.getUserName(),user.getPassword(),authorityList);
    }
}

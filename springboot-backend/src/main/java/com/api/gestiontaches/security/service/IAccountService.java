package com.api.gestiontaches.security.service;

import com.api.gestiontaches.entities.Employee;



public interface IAccountService {
    public void addRole(String role);
    //public void addUser(String userName, String password, String email , String name ,String lastname ,String occupation , Long salary, List<String> roles);
    public void addRoleToUser(String userName,String role);
    public Employee loadUserByUserName(String userName);
}

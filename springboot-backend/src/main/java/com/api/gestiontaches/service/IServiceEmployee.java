package com.api.gestiontaches.service;

import com.api.gestiontaches.entities.Employee;
import com.api.gestiontaches.security.entities.AppRole;

import java.util.List;

public interface IServiceEmployee {

    public void saveEmployee(Employee emp);
    public List<Employee> getAllEmployees();
    public void deleteEmployee(Long id);
    public Employee getEmployee(Long id);
    public void addUser(String userName, String password, String email , String name , String lastname , String occupation , Long salary , List<AppRole> roles);
    }

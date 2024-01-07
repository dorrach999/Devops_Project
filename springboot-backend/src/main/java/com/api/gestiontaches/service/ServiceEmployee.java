package com.api.gestiontaches.service;

import com.api.gestiontaches.dao.EmployeeRepository;
import com.api.gestiontaches.entities.Employee;
import com.api.gestiontaches.security.entities.AppRole;
import com.api.gestiontaches.security.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ServiceEmployee implements IServiceEmployee{
    PasswordEncoder passwordEncoder;
    private EmployeeRepository employeeRepository;
    private RoleRepository roleRepository;

    @Override
    public void saveEmployee(Employee emp){
        emp.setPassword(passwordEncoder.encode(emp.getPassword()));
        employeeRepository.save(emp);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }


    @Override
    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public void addUser(String userName, String password, String email , String name , String lastname , String occupation , Long salary , List<AppRole> roles) {
        Employee user=employeeRepository.findAppUserByUserName(userName) ;
        if (user!=null) throw new RuntimeException(("user existe"));
        Employee newUser = Employee.builder()
                .name(name)
                .userName(userName)
                .password(passwordEncoder.encode(password))
                .email(email)
                .lastname(lastname)
                .occupation(occupation)
                .salary(salary)
                .id(UUID.randomUUID().getLeastSignificantBits())
                .build();

        // Add the role to the user's roles list
        if (roles != null) {
            List<AppRole> appRoles = roleRepository.findByRoleIn(roles);
            newUser.getRoles().addAll(appRoles);
        }

        // Save the user
        employeeRepository.save(newUser);
    }
}

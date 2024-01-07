package com.api.gestiontaches.security.service;

import com.api.gestiontaches.dao.EmployeeRepository;
import com.api.gestiontaches.entities.Employee;
import com.api.gestiontaches.security.entities.AppRole;
import com.api.gestiontaches.security.repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
@Transactional
public class AccountService implements IAccountService{
    private EmployeeRepository employeeRepository;
    private RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public void addRole(String role) {
        roleRepository.save(AppRole.builder().role(role).build());
    }

   /* @Override
    public void addUser(String userName, String password, String email , String name , String lastname , String occupation , Long salary , List<String> roles) {
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
            List<AppRole> appRoles = roleRepository.findAllById(roles);
            newUser.getRoles().addAll(appRoles);
        }

        // Save the user
        employeeRepository.save(newUser);
    } */

    @Override
    public void addRoleToUser(String userName, String role) {
        Employee user=employeeRepository.findAppUserByUserName(userName) ;
        AppRole rol=roleRepository.findById(role).orElse(null);
        user.getRoles().add(rol);

    }

    @Override
    public Employee loadUserByUserName(String userName) {

        return employeeRepository.findAppUserByUserName(userName);
    }
}

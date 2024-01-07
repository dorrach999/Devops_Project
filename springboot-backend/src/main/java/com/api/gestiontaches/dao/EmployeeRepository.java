package com.api.gestiontaches.dao;

import com.api.gestiontaches.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    public Employee findAppUserByUserName(String userName);

}

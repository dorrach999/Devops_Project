package com.api.gestiontaches.security.repository;

import com.api.gestiontaches.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<AppRole,String> {
    //List<AppRole> findAllById(List<AppRole> roles);
    List<AppRole> findByRoleIn(List<AppRole> roles);

}

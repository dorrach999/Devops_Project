package com.api.gestiontaches.entities;

import com.api.gestiontaches.security.entities.AppRole;
// import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //auto inc id(primary)
    private Long id ;
    private String name ;
    private String lastname ;
    private String email ;
    private Long salary ;
    private String occupation ;

    @Builder.Default
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppRole> roles = new ArrayList<>();;
    @OneToMany(mappedBy = "employee" , cascade = CascadeType.ALL)
    @JsonIgnore
    List<Task> tasks;
    @Column(unique = true)
    private String userName;
    private String password;


}

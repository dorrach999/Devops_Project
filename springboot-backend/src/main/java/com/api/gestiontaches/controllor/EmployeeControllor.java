package com.api.gestiontaches.controllor;

import com.api.gestiontaches.entities.Employee;
import com.api.gestiontaches.security.entities.AppRole;
import com.api.gestiontaches.security.repository.RoleRepository;
import com.api.gestiontaches.service.IServiceEmployee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;


import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
public class EmployeeControllor {
    IServiceEmployee serviceEmployee;
    RoleRepository roleRepository;
    @GetMapping("/employee/all")
    public List<Employee> getAllEmployees(){
        List<Employee> liste=serviceEmployee.getAllEmployees();
        return liste;
    }

    @GetMapping("/employee/remove/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long idP)
    {
        serviceEmployee.deleteEmployee(idP);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    /*@PostMapping("/employee/add")
    public void saveProduct(@RequestParam("employee") String e ) throws IOException {
        Employee employee=new ObjectMapper().readValue(e,Employee.class);
        serviceEmployee.saveEmployee(employee);
    } */

    @PostMapping("/employee/add")
    public void saveProduct(@RequestBody String e) throws IOException {
        Employee employee = new ObjectMapper().readValue(e, Employee.class);

        // Assuming roles are provided as a list in the request
        List<AppRole> roles = employee.getRoles(); // Extract roles from the employee object

        // Check if roles are provided, and then add the user with roles
        if (roles != null && !roles.isEmpty()) {
            serviceEmployee.addUser(employee.getUserName(), employee.getPassword(), employee.getEmail(),
                    employee.getName(), employee.getLastname(), employee.getOccupation(),
                    employee.getSalary(), roles);
        } else {
            // Add the user with no roles
            serviceEmployee.saveEmployee(employee);
        }
    }

   /* @PutMapping("/employee/update/{id}")
    public void update(@RequestParam("employee") String e ,@PathVariable("id") Long id) throws IOException, ChangeSetPersister.NotFoundException {
        Employee employee= serviceEmployee.getEmployee(id);
        if (employee != null) {
            Employee updatedEmployee = new ObjectMapper().readValue(e, Employee.class);
            updatedEmployee.setId(id); // Set the ID to ensure it's the same as the existing employee
            serviceEmployee.saveEmployee(updatedEmployee);
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
        serviceEmployee.saveEmployee(employee);
    }*/

    @PutMapping("/employee/edit/{id}")
    public void updateEmployee(@PathVariable("id") Long id, @RequestParam("updatedEmployee") String updatedEmployeeJson)
            throws IOException, ChangeSetPersister.NotFoundException {
        Employee existingEmployee = serviceEmployee.getEmployee(id);

        if (existingEmployee != null) {
            // Convert the JSON string to a map
            Map<String, Object> updateFields = new ObjectMapper().readValue(updatedEmployeeJson,
                    new TypeReference<Map<String, Object>>() {});

            // Update the fields
            for (Map.Entry<String, Object> entry : updateFields.entrySet()) {
                String fieldName = entry.getKey();
                Object fieldValue = entry.getValue();

                // Use reflection to set the field value
                try {
                    Field field = Employee.class.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    field.set(existingEmployee, fieldValue);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    // Handle exceptions as needed
                    e.printStackTrace();
                }
            }
            // Set the ID to ensure it's the same as the existing employee
            existingEmployee.setId(id);

            // Save the updated employee
            serviceEmployee.saveEmployee(existingEmployee);
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @PutMapping("/employee/update/{id}")
    public void editEmployee(@PathVariable("id") Long id, @RequestParam("updatedEmployee") String updatedEmployeeJson)
            throws IOException, ChangeSetPersister.NotFoundException {
        Employee existingEmployee = serviceEmployee.getEmployee(id);

        if (existingEmployee != null) {
            // Convert the JSON string to a map
            Map<String, Object> updateFields = new ObjectMapper().readValue(updatedEmployeeJson,
                    new TypeReference<Map<String, Object>>() {});

            // Update the fields
            for (Map.Entry<String, Object> entry : updateFields.entrySet()) {
                String fieldName = entry.getKey();
                Object fieldValue = entry.getValue();

                // Handle roles separately
                if ("roles".equals(fieldName)) {
                    List<AppRole> roleNames = (List<AppRole>) fieldValue;
                    List<AppRole> roles = roleRepository.findByRoleIn(roleNames);
                    existingEmployee.setRoles(roles);
                } else {
                    // Use reflection to set the field value for non-role fields
                    try {
                        Field field = Employee.class.getDeclaredField(fieldName);
                        field.setAccessible(true);
                        field.set(existingEmployee, fieldValue);
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        // Handle exceptions as needed
                        e.printStackTrace();
                    }
                }
            }

            // Set the ID to ensure it's the same as the existing employee
            existingEmployee.setId(id);

            // Save the updated employee
            serviceEmployee.saveEmployee(existingEmployee);
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

}


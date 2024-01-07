package com.api.gestiontaches.controllor;

import com.api.gestiontaches.entities.Task;
import com.api.gestiontaches.service.IServiceTask;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
public class TaskControllor {
    IServiceTask serviceTask;
    
    @GetMapping("/task/all")
    public List<Task> getAllTasks(){
        List<Task> liste=serviceTask.getAllTasks();
        return liste;
    }

    @GetMapping("/employee/tasks/{id}")
    public List<Task> getEmpTasks(@PathVariable("id") Long id){
        List<Task> liste=serviceTask.getTasksByIdEmp(id);
        return liste;
    }

    @GetMapping("/Task/remove/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Long idP)
    {
        serviceTask.deleteTask(idP);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    @PostMapping("/Task/add")
    public void savePTask(@RequestParam("Task") String t ) throws IOException {
        Task task=new ObjectMapper().readValue(t,Task.class);
        serviceTask.saveTask(task);
    }

    @PutMapping("/Task/update/{id}")
    public void update(@RequestParam("Task") String e ,@PathVariable("id") Long id) throws IOException, ChangeSetPersister.NotFoundException {
        Task task= serviceTask.getTask(id);
        if (task != null) {
            Task updatedTask = new ObjectMapper().readValue(e, Task.class);
            updatedTask.setId(id); // Set the ID to ensure it's the same as the existing Task
            serviceTask.saveTask(updatedTask);
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
        serviceTask.saveTask(task);
    }
}

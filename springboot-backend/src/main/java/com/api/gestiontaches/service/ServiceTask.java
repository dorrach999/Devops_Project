package com.api.gestiontaches.service;

import com.api.gestiontaches.dao.TaskRepository;
import com.api.gestiontaches.entities.Task;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceTask implements IServiceTask{
    private TaskRepository taskRepository;
    @Override
    public void saveTask(Task t) {
        taskRepository.save(t);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task getTask(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public List<Task> getTasksByIdEmp(Long idEmp) {
        return taskRepository.getTasksByIdEmp(idEmp);
    }

}

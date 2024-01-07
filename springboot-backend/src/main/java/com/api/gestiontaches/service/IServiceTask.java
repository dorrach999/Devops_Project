package com.api.gestiontaches.service;

import com.api.gestiontaches.entities.Task;

import java.util.List;

public interface IServiceTask {
    public void saveTask(Task t);
    public List<Task> getAllTasks();
    public void deleteTask(Long id);
    public Task getTask(Long id);
    public List<Task> getTasksByIdEmp(Long idEmp);
}

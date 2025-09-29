package com.example.todo.service;

import com.example.todo.model.Task;

import java.util.List;

public interface TaskService {
    Task create(Task task);
    Task update(Long id, Task task);
    void delete(Long id);
    Task getById(Long id);
    List<Task> getAll();
}

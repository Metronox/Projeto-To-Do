package com.example.todo.service.impl;

import com.example.todo.exception.ResourceNotFoundException;
import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import com.example.todo.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task create(Task task) {
        if (task.getCompleted() == null) {
            task.setCompleted(false);
        }
        return repository.save(task);
    }

    @Override
    public Task update(Long id, Task task) {
        Task existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada: " + id));
        if (task.getTitle() != null) existing.setTitle(task.getTitle());
        existing.setDescription(task.getDescription());
        existing.setObservations(task.getObservations());
        if (task.getCompleted() != null) existing.setCompleted(task.getCompleted());
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        Task existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada: " + id));
        repository.delete(existing);
    }

    @Override
    public Task getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada: " + id));
    }

    @Override
    public List<Task> getAll() {
        return repository.findAll();
    }
}

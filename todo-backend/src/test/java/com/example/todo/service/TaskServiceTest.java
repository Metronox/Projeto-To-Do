package com.example.todo.service;

import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import com.example.todo.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TaskServiceTest {
    private TaskRepository repository;
    private TaskService service;

    @BeforeEach
    void setup() {
        repository = Mockito.mock(TaskRepository.class);
        service = new TaskServiceImpl(repository);
    }

    @Test
    void create_shouldSetDefaultCompleted() {
        Task t = new Task();
        t.setTitle("Teste");
        when(repository.save(any(Task.class))).thenAnswer(i -> i.getArgument(0));

        Task created = service.create(t);
        assertNotNull(created.getCompleted());
        assertFalse(created.getCompleted());
        verify(repository, times(1)).save(any(Task.class));
    }

    @Test
    void update_nonexistent_shouldThrow() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        Task t = new Task();
        assertThrows(RuntimeException.class, () -> service.update(1L, t));
    }
}

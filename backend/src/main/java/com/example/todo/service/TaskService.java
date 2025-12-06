package com.example.todo.service;

import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;

    public Task create(Task t) {
        return repository.save(t);
    }

    public List<Task> latest(int limit) {
        return repository.findByCompletedFalseOrderByCreatedAtDesc(PageRequest.of(0, Math.max(1, limit)));
    }

    public void markCompleted(Long id) {
        Task t = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        t.setCompleted(true);
        repository.save(t);
    }
}

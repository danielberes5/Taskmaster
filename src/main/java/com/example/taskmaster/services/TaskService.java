package com.example.taskmaster.services;

import com.example.taskmaster.models.Task;
import com.example.taskmaster.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Optional<Task> getById(Long id) {
        return taskRepository.findById(id);
    };

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public Task save(Task task){
        if(task.getId() == null){
            task.setCreatedAt(LocalDateTime.now());
        }
        else {
            task.setUpdatedAt(LocalDateTime.now());
        }

        return taskRepository.save(task);
    }

    public void remove(Task task){
        task.setDeleted(true);
        taskRepository.save(task);
    }
}

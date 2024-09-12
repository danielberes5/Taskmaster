package com.example.taskmaster.controllers;

import com.example.taskmaster.models.Account;
import com.example.taskmaster.models.Task;
import com.example.taskmaster.services.AccountService;
import com.example.taskmaster.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/tasks/{id}")
    public String getTask(@PathVariable Long id, Model model){
        Optional<Task> optionalTask = taskService.getById(id);
        if(optionalTask.isPresent()){
            model.addAttribute("task",optionalTask.get());
            return "task";
        }
        else{
            return "404";
        }
    }

    @GetMapping("/tasks/new")
    public String createNewTask(Model model){
        Optional<Account> optionalAccount = accountService.findByEmail("user.user@domain.com");
        if(optionalAccount.isPresent()){
            Task task = new Task(optionalAccount.get());
            model.addAttribute("task", task);
            return "task_new";
        }
        else {
            return "404";
        }
    }

    @PostMapping("/tasks/new")
    public String saveNewTask(@ModelAttribute Task task){
        taskService.save(task);
        return "redirect:/tasks/" + task.getId();
    }

    @GetMapping("/tasks/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String getTaskForEdit(@PathVariable Long id, Model model){
        Optional<Task> optionalTask = taskService.getById(id);
        if(optionalTask.isPresent()){
            model.addAttribute("task",optionalTask.get());
            return "task_edit";
        }
        else{
            return "404";
        }
    }

    @PutMapping("/tasks/{id}")
    @PreAuthorize("isAuthenticated()")
    public String updateTask(@PathVariable Long id, Task task, BindingResult result, Model model){
        Optional<Task> optionalTask = taskService.getById(id);
        if(optionalTask.isPresent()){
            Task existingTask = optionalTask.get();

            existingTask.setTitle(task.getTitle());
            existingTask.setBody(task.getBody());
            existingTask.setStatus(task.getStatus());

            taskService.save(existingTask);

        }
        return "redirect:/tasks/" + task.getId();
    }

    @GetMapping("/tasks/{id}/delete")
    @PreAuthorize("isAuthenticated()")
    public String deleteTask(@PathVariable Long id){
        Optional<Task> optionalTask = taskService.getById(id);
        if(optionalTask.isPresent()){

            taskService.remove(optionalTask.get());
            return "redirect:/";

        }
        else{
            return "404";
        }
    }




}

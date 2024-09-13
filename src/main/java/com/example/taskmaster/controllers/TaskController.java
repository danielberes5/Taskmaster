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

import java.security.Principal;
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
    @PreAuthorize("isAuthenticated()")
    public String createNewTask(Model model){

        Task task = new Task();
        model.addAttribute("task", task);
        return "task_new";
    }

    @PostMapping("/tasks/new")
    @PreAuthorize("isAuthenticated()")
    public String saveNewTask(@ModelAttribute Task task, Principal principal){

        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }

        Account account = accountService.findByEmail(authUsername).orElseThrow(() -> new IllegalArgumentException("Account not found"));

        task.setAccount(account);
        taskService.save(task);
        return "redirect:/";
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
    public String updateTask(@PathVariable Long id, Task task, BindingResult result, Model model, Principal principal){
        Optional<Task> optionalTask = taskService.getById(id);
        if(optionalTask.isPresent()){

            String authUsername = "anonymousUser";
            if (principal != null) {
                authUsername = principal.getName();
            }

            Task existingTask = optionalTask.get();

            if(!authUsername.equals(existingTask.getAccount().getEmail())){
                throw new IllegalArgumentException("Account not match");
            }

            existingTask.setTitle(task.getTitle());
            existingTask.setBody(task.getBody());
            existingTask.setStatus(task.getStatus());

            taskService.save(existingTask);

        }
        return "redirect:/tasks/" + task.getId();
    }

    @GetMapping("/tasks/{id}/delete")
    @PreAuthorize("isAuthenticated()")
    public String deleteTask(@PathVariable Long id, Principal principal){
        Optional<Task> optionalTask = taskService.getById(id);
        if(optionalTask.isPresent()){

            String authUsername = "anonymousUser";
            if (principal != null) {
                authUsername = principal.getName();
            }

            Task existingTask = optionalTask.get();

            if(!authUsername.equals(existingTask.getAccount().getEmail())){
                throw new IllegalArgumentException("Account not match");
            }

            taskService.remove(optionalTask.get());
            return "redirect:/";

        }
        else{
            return "404";
        }
    }




}

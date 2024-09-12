package com.example.taskmaster.controllers;

import com.example.taskmaster.models.Task;
import com.example.taskmaster.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public String home(Model model){
        List<Task> tasks = taskService.getAll();
        model.addAttribute("tasks", tasks);
        return "home";
    }
}

package com.example.taskmaster.config;

import com.example.taskmaster.models.Account;
import com.example.taskmaster.models.Authority;
import com.example.taskmaster.models.Task;
import com.example.taskmaster.repositories.AuthorityRepository;
import com.example.taskmaster.services.AccountService;
import com.example.taskmaster.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private TaskService taskService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public void run(String... args) throws Exception {

        List<Task> posts = taskService.getAll();

        if (posts.size() == 0) {

            Authority user = new Authority();
            user.setName("ROLE_USER");
            authorityRepository.save(user);

            Authority admin = new Authority();
            admin.setName("ROLE_ADMIN");
            authorityRepository.save(admin);

            Account account1 = Account
                    .builder()
                    .firstName("user_first")
                    .lastName("user_last")
                    .email("user.user@domain.com")
                    .password("password")
                    .build();

            Set<Authority> authorities1 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities1::add);
            account1.setAuthorities(authorities1);

            Account account2 = Account
                    .builder()
                    .firstName("admin_first")
                    .lastName("admin_last")
                    .email("admin.admin@domain.com")
                    .password("password")
                    .build();

            Set<Authority> authorities2 = new HashSet<>();
            authorityRepository.findById("ROLE_ADMIN").ifPresent(authorities2::add);
            //authorityRepository.findById("ROLE_USER").ifPresent(authorities2::add);
            account2.setAuthorities(authorities2);

            accountService.save(account1);
            account2 = accountService.save(account2);

            Task task = new Task(account2);
            task.setBody("body");
            task.setTitle("title");
            taskService.save(task);


        }
    }
}

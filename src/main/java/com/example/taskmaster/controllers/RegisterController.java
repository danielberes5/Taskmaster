package com.example.taskmaster.controllers;

import com.example.taskmaster.models.Account;
import com.example.taskmaster.models.Authority;
import com.example.taskmaster.repositories.AuthorityRepository;
import com.example.taskmaster.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
public class RegisterController {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private AccountService accountService;

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        Account account = new Account();
        model.addAttribute("account", account);
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute Account account){

        Optional<Authority> optionalAuthority = authorityRepository.findById("ROLE_USER");
        Authority user = new Authority();

        if(optionalAuthority.isEmpty()){

            user.setName("ROLE_USER");
            user = authorityRepository.save(user);
        }
        else {
            user = optionalAuthority.get();
        }



        Set<Authority> authorities = new HashSet<>();
        authorities.add(user);
        account.setAuthorities(authorities);

        accountService.save(account);

        return "redirect:/";
    }
}

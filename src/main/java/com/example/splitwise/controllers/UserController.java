package com.example.splitwise.controllers;

import com.example.splitwise.models.User;
import com.example.splitwise.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserRepository userRepository;

    UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody User getUser(@PathVariable(name="id") Long id){
        System.out.print("requested user");
        return userRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}

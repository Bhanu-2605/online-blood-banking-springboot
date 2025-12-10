package com.example.bloodbank.controller;

import com.example.bloodbank.model.User;
import com.example.bloodbank.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class AuthController {
    private final UserService userService;
    public AuthController(UserService userService) { this.userService = userService; }

    @GetMapping("/register")
    public String registerForm() { return "register"; }

    @PostMapping("/register")
    public String register(User user, Model m) {
        try {
            userService.register(user);
            m.addAttribute("msg", "Registered successfully. Please login.");
            return "login";
        } catch (Exception e) {
            m.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/login")
    public String loginForm() { return "login"; }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model m) {
        try {
            User user = userService.login(email, password);
            m.addAttribute("user", user);
            return "dashboard";
        } catch (Exception e) {
            m.addAttribute("error", e.getMessage());
            return "login";
        }
    }
}

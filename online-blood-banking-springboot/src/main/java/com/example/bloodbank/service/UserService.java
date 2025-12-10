package com.example.bloodbank.service;

import com.example.bloodbank.model.User;
import com.example.bloodbank.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repo;
    public UserService(UserRepository repo) { this.repo = repo; }

    public User register(User user) {
        // basic checks
        if (user.getEmail() == null) throw new RuntimeException("Email required");
        Optional<User> ex = repo.findByEmail(user.getEmail());
        if (ex.isPresent()) throw new RuntimeException("Email already exists");
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        if (user.getRole() == null) user.setRole("DONOR");
        return repo.save(user);
    }

    public User login(String email, String password) {
        Optional<User> u = repo.findByEmail(email);
        if (u.isEmpty()) throw new RuntimeException("Invalid credentials");
        User user = u.get();
        if (!BCrypt.checkpw(password, user.getPassword())) throw new RuntimeException("Invalid credentials");
        // Not using JWT/session for simplicity; return user object
        return user;
    }
}

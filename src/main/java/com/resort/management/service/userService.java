package com.resort.management.service;

import com.resort.management.model.User;
import com.resort.management.repository.userRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userService {

    private final userRepository repo;

    public userService(userRepository repo) {
        this.repo = repo;
    }

    public User saveUser(User user) {
        return repo.save(user);
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public User getUserById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void deleteUser(Long id) {
        repo.deleteById(id);
    }
}

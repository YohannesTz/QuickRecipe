package com.github.yohannestz.QuickRecipe.controllers;

import com.github.yohannestz.QuickRecipe.domain.User;
import com.github.yohannestz.QuickRecipe.exception.ResourceNotFoundException;
import com.github.yohannestz.QuickRecipe.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable Long id) throws ResourceNotFoundException{
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    // moved to AuthController
    /*@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }*/

    @DeleteMapping("/id/{id}")
    public Map<String, String> deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        User candidateUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        userRepository.delete(candidateUser);
        Map<String, String> response = new HashMap<>();
        response.put("message", "successfully deleted user");
        return response;
    }
}

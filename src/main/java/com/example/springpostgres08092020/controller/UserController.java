package com.example.springpostgres08092020.controller;

import com.example.springpostgres08092020.model.User;
import com.example.springpostgres08092020.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/get-all-users")
    public List<User> getUsers(){
        List<User> allUsersList = userRepository.findAll();
        return allUsersList;
    }

    @GetMapping("/get-user/{id}")
    public User getUser(@PathVariable(value = "id") Integer userId) {
        User userEntity = userRepository.findById(userId).get();
        return userEntity;
    }

    @PostMapping("/create-user")
    public User createUser(@RequestBody User user) {
        User savedUser =  userRepository.save(user);
        return savedUser;
    }



    @PutMapping("/update-user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Integer UserId,
                                           @RequestBody User userDetails) {
        User user =userRepository.findById(UserId).get();

        user.setName(userDetails.getName());
        user.setLastname1(userDetails.getLastname1());
        user.setLastname2(userDetails.getLastname2());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());

        User updatedUser = userRepository.save(user);

        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete-user/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Integer userId)
    {
        User user = userRepository.findById(userId).get();

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}

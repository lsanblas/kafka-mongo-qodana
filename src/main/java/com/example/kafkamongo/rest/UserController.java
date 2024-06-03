package com.example.kafkamongo.rest;

import com.example.kafkamongo.entities.User;
import com.example.kafkamongo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return this.userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public User getUser(@PathVariable String id){
        return this.userService.getUserById(id);
    }
    @GetMapping("/search/{email}")
    public User findUser(@PathVariable String email){
        return this.userService.getUserByEmail(email);
    }
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody User user){this.userService.createUser(user);}
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateUser(@RequestBody User user){this.userService.updateUser(user);}
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable String id){this.userService.deleteUser(id);}
}

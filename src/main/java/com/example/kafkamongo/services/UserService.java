package com.example.kafkamongo.services;

import com.example.kafkamongo.entities.User;
import com.example.kafkamongo.exceptions.UserNotFoundException;
import com.example.kafkamongo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public User getUserById(String id){
        return this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User getUserByEmail(String email){
        return this.userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
    }

    public void createUser(User user){
        user.setCreated(LocalDateTime.now());
        this.userRepository.save(user);
    }

    public void updateUser(User user){
        this.userRepository.save(user);
    }

    public void deleteUser(String id){
        this.userRepository.deleteById(id);
    }
}

package com.example.neuro_learn.neuro_learn.Services.Impl;



import java.util.Optional;

import javax.swing.ScrollPaneLayout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.neuro_learn.neuro_learn.Entities.User;
import com.example.neuro_learn.neuro_learn.Repositories.UserRepo;
import com.example.neuro_learn.neuro_learn.Services.UserService;
import com.example.neuro_learn.neuro_learn.utils.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Override
    public User saveUser(User user) {

        Optional<User> existingUser = userRepo.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new ResourceNotFoundException("User", "Email", user.getEmail(), true);
        }
        
       User savedUser= this.userRepo.save(user);
       return savedUser;

    }

    @Override
    public User getUserByEmailAndPassword(String email, String passsword) {
           Optional<User> existingUser = userRepo.findByEmail(email);
        if(existingUser.isEmpty()) {
            throw new ResourceNotFoundException("User", "Email", email, false);
        }
        System.out.println("User found: " + existingUser.get().getEmail());
        System.out.println("User password: " + existingUser.get().getPassword());
        System.out.println("Provided password: " + passsword);
        String user_password= existingUser.get().getPassword();
        if (existingUser.isPresent() && user_password.equals(passsword)) {
            return existingUser.get();
        } else {
            throw new ResourceNotFoundException("User", "Email", email, false);
        }
       
    }

}

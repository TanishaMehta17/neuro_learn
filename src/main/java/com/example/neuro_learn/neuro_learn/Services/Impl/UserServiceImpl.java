// package com.example.neuro_learn.neuro_learn.Services.Impl;

// import java.util.Optional;


// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// import com.example.neuro_learn.neuro_learn.Entities.User;
// import com.example.neuro_learn.neuro_learn.Repositories.UserRepo;
// import com.example.neuro_learn.neuro_learn.Services.UserService;
// import com.example.neuro_learn.neuro_learn.utils.ResourceNotFoundException;

// @Service
// public class UserServiceImpl implements UserService {

//     @Autowired
//     private UserRepo userRepo;

//     @Autowired
//     private PasswordEncoder passwordEncoder;

//     @Override
// public User saveUser(User user) {
//     Optional<User> existingUser = userRepo.findByEmail(user.getEmail());
//     if (existingUser.isPresent()) {
//         throw new ResourceNotFoundException("User", "Email", user.getEmail(), true);
//     }
    
//     // Encode password before saving (if not already)
//     user.setPassword(passwordEncoder.encode(user.getPassword()));
    
//     return this.userRepo.save(user);
// }


// @Override
// public User getUserByEmailAndPassword(String email, String password) {
//     Optional<User> existingUser = userRepo.findByEmail(email);
//     if (existingUser.isEmpty()) {
//         throw new ResourceNotFoundException("User", "Email", email, false);
//     }

//     User user = existingUser.get();
//     System.out.println("User found: " + user.getEmail());
//     System.out.println("Encoded password from DB: " + user.getPassword()); // The hash in DB
//     System.out.println("Provided password: " + password); // The plain-text password entered during login

//     // Verify the password
//     if (passwordEncoder.matches(password, user.getPassword())) {
//         return user;
//     } else {
//         throw new ResourceNotFoundException("User", "Password", "Invalid", false);
//     }
// }


// }
package com.example.neuro_learn.neuro_learn.Services.Impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.neuro_learn.neuro_learn.Entities.User;
import com.example.neuro_learn.neuro_learn.Repositories.UserRepo;
import com.example.neuro_learn.neuro_learn.Services.UserService;
import com.example.neuro_learn.neuro_learn.utils.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        Optional<User> existingUser = userRepo.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new ResourceNotFoundException("User", "Email", user.getEmail(), true);
        }
        // Encode password before saving (if not already)
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepo.save(user);
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        Optional<User> existingUser = userRepo.findByEmail(email);
        if (existingUser.isEmpty()) {
            System.out.println("User not found for email: " + email);
            throw new ResourceNotFoundException("User", "Email", email, false);
        }

        User user = existingUser.get();
        System.out.println("User found: " + user.getEmail());
        System.out.println("Encoded password from DB: " + user.getPassword()); // The hash in DB
        System.out.println("Provided password: " + password); // The plain-text password entered during login

        // Verify the password
        if (passwordEncoder.matches(password, user.getPassword())) {
            System.out.println("Password matches.");
            return user;
        } else {
            System.out.println("Invalid password.");
            throw new ResourceNotFoundException("User", "Password", "Invalid", false);
        }
    }
}

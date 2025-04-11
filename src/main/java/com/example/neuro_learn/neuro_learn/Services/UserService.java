package com.example.neuro_learn.neuro_learn.Services;

import com.example.neuro_learn.neuro_learn.Entities.User;

public interface UserService {
         User saveUser(User user);
         User getUserByEmailAndPassword(String email, String passsword);       
}

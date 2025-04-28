package com.example.neuro_learn.neuro_learn.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

import com.example.neuro_learn.neuro_learn.Entities.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{
    Optional<User> findByEmail(String email);
    Optional<User>findById(Long id);
}

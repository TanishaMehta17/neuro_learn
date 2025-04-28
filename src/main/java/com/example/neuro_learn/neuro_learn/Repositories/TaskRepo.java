package com.example.neuro_learn.neuro_learn.Repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.neuro_learn.neuro_learn.Entities.Task;
import com.example.neuro_learn.neuro_learn.Entities.User;


public interface TaskRepo extends JpaRepository<Task,Long>{
    Optional<Task> findById(int taskId);
    List<Task> findByUser(User user);
 
}

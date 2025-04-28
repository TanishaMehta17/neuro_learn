package com.example.neuro_learn.neuro_learn.Services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.neuro_learn.neuro_learn.Entities.Task;
import com.example.neuro_learn.neuro_learn.Entities.User;
import com.example.neuro_learn.neuro_learn.Repositories.TaskRepo;
import com.example.neuro_learn.neuro_learn.Repositories.UserRepo;
import com.example.neuro_learn.neuro_learn.Services.TaskService;


@Service
public class TaskServiceImpl    implements TaskService {
    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private UserRepo userRepository; 

    @Override
    public Task saveTask(Task task) {
 
      Long userId = task.getUser().getId();
      User existingUser = userRepository.findById(userId)
              .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
      task.setUser(existingUser);

      return taskRepo.save(task);    
    }


    @Override
    public void deleteTask(Long taskId, Long userId) {
        Optional<Task> taskOpt = taskRepo.findById(taskId);
        if (taskOpt.isPresent()) {
            Task task = taskOpt.get();
            if (task.getUser().getId().equals(userId)) {
                taskRepo.deleteById(taskId);
            } else {
                throw new RuntimeException("Task does not belong to the given user");
            }
        } else {
            throw new RuntimeException("Task not found");
        }
    }

    @Override
    public List<Task> getAllTasks(Long userId) {
        User user = userRepository.findById((long) userId)
        .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

return taskRepo.findByUser(user);
    }


    @Override
    public Task markTaskCompleted(Long taskId) {
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setIsCompleted(true);
        return taskRepo.save(task);
    }
    
   
  
   
}

package com.example.neuro_learn.neuro_learn.Controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.neuro_learn.neuro_learn.Entities.Task;
import com.example.neuro_learn.neuro_learn.Entities.User;
import com.example.neuro_learn.neuro_learn.Repositories.TaskRepo;
import com.example.neuro_learn.neuro_learn.Repositories.UserRepo;
import com.example.neuro_learn.neuro_learn.Services.TaskService;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    

    @Autowired
    private TaskService taskService;

   

  
    @PostMapping("/saveTask")
    public ResponseEntity<Task> saveTask(@RequestBody Task task) {
        Task savedTask = taskService.saveTask(task);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

   
    @GetMapping("/getAll")
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam Long userId) {
        List<Task> tasks = taskService.getAllTasks(userId);
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/update")
    public ResponseEntity<Task> markTaskAsCompleted(@RequestParam Long taskId) {
        Task updatedTask = taskService.markTaskCompleted(taskId);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTask(@RequestParam Long taskId, @RequestParam Long userId) {
        taskService.deleteTask(taskId, userId);
        return ResponseEntity.ok("Task deleted successfully");
    }
    
}

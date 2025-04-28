package com.example.neuro_learn.neuro_learn.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.neuro_learn.neuro_learn.Entities.Task;


public interface TaskService {
    Task saveTask(Task task);
    void deleteTask(Long taskId, Long userId);
    List<Task> getAllTasks(Long userId);
    Task markTaskCompleted(Long taskId);
}

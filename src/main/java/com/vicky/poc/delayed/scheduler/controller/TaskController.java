package com.vicky.poc.delayed.scheduler.controller;

import com.vicky.poc.delayed.scheduler.dto.TaskRequest;
import com.vicky.poc.delayed.scheduler.service.TaskService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/event")
    public ResponseEntity<String> scheduleTask(@RequestBody TaskRequest request) {
        taskService.scheduleTask(request);
        return ResponseEntity.ok("Task scheduled successfully.");
    }
}


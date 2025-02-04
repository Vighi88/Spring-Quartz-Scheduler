package com.vicky.poc.delayed.scheduler.components;

import com.vicky.poc.delayed.scheduler.entity.Task;
import com.vicky.poc.delayed.scheduler.repository.TaskRepository;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.util.Optional;

@Component
public class ExecuteTaskJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(ExecuteTaskJob.class);

    private final TaskRepository taskRepository;

    public ExecuteTaskJob(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void execute(JobExecutionContext context) {
        JobDataMap dataMap = context.getMergedJobDataMap();
        Long taskId = dataMap.getLong("taskId");

        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isEmpty()) {
            logger.warn("Task with ID {} not found.", taskId);
            return;
        }

        Task task = optionalTask.get();
        logger.info("Executing Task ID: {}, Type: {}, Service: {}", task.getId(), task.getType(), task.getServiceName());

        // Simulating event sent to WiFi service
        logger.info("🚀 Sending event to WiFi service for Task ID: {} | Target Topic: {} | Body: {}",
                task.getId(), task.getTargetTopic(), task.getBody());

        // Mark task as completed after receiving the result event with task ID from Wifi service
        task.setStatus("COMPLETED");
        task.setExecutionTime(Instant.now().toEpochMilli() - task.getExecuteAt().toEpochMilli());
        taskRepository.save(task);
        logger.info("✅ Task {} execution completed in {} ms", task.getId(), task.getExecutionTime());
    }
}


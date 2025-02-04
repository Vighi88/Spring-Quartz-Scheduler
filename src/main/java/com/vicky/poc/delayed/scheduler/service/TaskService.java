package com.vicky.poc.delayed.scheduler.service;

import com.vicky.poc.delayed.scheduler.components.ExecuteTaskJob;
import com.vicky.poc.delayed.scheduler.dto.TaskRequest;
import com.vicky.poc.delayed.scheduler.entity.Task;
import com.vicky.poc.delayed.scheduler.repository.TaskRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Date;

@Service
public class TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;

    private final Scheduler scheduler;

    @Autowired
    public TaskService(TaskRepository taskRepository, Scheduler scheduler) {
        this.taskRepository = taskRepository;
        this.scheduler = scheduler;
    }

    @PostConstruct
    public void init() {
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            logger.error("Failed to start Quartz scheduler", e);
        }
    }

    public void scheduleTask(TaskRequest request) {
        Task task = new Task();
        task.setRequestId(request.getRequestId());
        task.setExecuteAt(Instant.now().plusSeconds(request.getDelayInSeconds()));  // Delay in seconds
        task.setType(request.getType());
        task.setBody(request.getBody());
        task.setServiceName(request.getServiceName());
        task.setTargetTopic(request.getTargetTopic());
        task.setStatus("SCHEDULED");
        task.setCreatedAt(Instant.now());
        taskRepository.save(task);

        scheduleQuartzJob(task);
    }

    private void scheduleQuartzJob(Task task) {
        try {
            JobDetail jobDetail = JobBuilder.newJob(ExecuteTaskJob.class)
                    .withIdentity("task_" + task.getId(), "TaskGroup")
                    .usingJobData("taskId", task.getId())
                    .storeDurably() // Store job in Quartz DB
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("taskTrigger_" + task.getId(), "TaskGroup")
                    .startAt(Date.from(task.getExecuteAt()))
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);
            logger.info("Task {} scheduled to execute at {}", task.getId(), task.getExecuteAt());

        } catch (SchedulerException e) {
            logger.error("Failed to schedule Quartz job for task {}", task.getId(), e);
        }
    }

    @PreDestroy
    public void preDestroy() {
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            logger.error("Failed to shutdown Quartz scheduler", e);
        }
    }
}


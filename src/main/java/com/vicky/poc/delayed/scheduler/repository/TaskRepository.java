package com.vicky.poc.delayed.scheduler.repository;

import com.vicky.poc.delayed.scheduler.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.Instant;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    // Find all tasks that are scheduled to run at or before the current time
    List<Task> findByExecuteAtBeforeAndStatus(Instant now, String status);
}


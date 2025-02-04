package com.vicky.poc.delayed.scheduler.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "x_request_id", nullable = false)
    private String requestId;

    @Column(name = "execute_at", nullable = false)
    private Instant executeAt;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "body", columnDefinition = "TEXT")
    private String body;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "target_topic")
    private String targetTopic;

    @Column(name = "status")
    private String status;

    @Column(name = "result_message")
    private String resultMessage;

    @Column(name = "execution_time_in_millis")
    private Long executionTime;

    @Column(name = "name")
    private String name;

    @Column(name = "execution_type")
    private String executionType;

    @Column(name = "lgi_customer_id")
    private String customerId;

    @Column(name = "country")
    private String country;

    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @Column(name = "feedback_required")
    private Boolean feedbackRequired;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Instant getExecuteAt() {
        return executeAt;
    }

    public void setExecuteAt(Instant executeAt) {
        this.executeAt = executeAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getTargetTopic() {
        return targetTopic;
    }

    public void setTargetTopic(String targetTopic) {
        this.targetTopic = targetTopic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExecutionType() {
        return executionType;
    }

    public void setExecutionType(String executionType) {
        this.executionType = executionType;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getFeedbackRequired() {
        return feedbackRequired;
    }

    public void setFeedbackRequired(Boolean feedbackRequired) {
        this.feedbackRequired = feedbackRequired;
    }
}


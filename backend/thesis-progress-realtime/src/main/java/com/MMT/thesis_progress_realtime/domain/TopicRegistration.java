package com.MMT.thesis_progress_realtime.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity @Table(name="topic_registrations")
public class TopicRegistration {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="topic_id", nullable = false)
    private Long topicId;

    @Column(name="student_id", nullable = false)
    private Long studentId;

    @Column(nullable=false) 
    private String status = "PENDING";

    @Column(columnDefinition="TEXT")
    private String reason;

    @Column(name="created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name="decided_at")
    private LocalDateTime decidedAt;

    @Column(name="decided_by")
    private Long decidedBy;

    // Getters
    public Long getId() { return id; }
    public Long getTopicId() { return topicId; }
    public Long getStudentId() { return studentId; }
    public String getStatus() { return status; }
    public String getReason() { return reason; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getDecidedAt() { return decidedAt; }
    public Long getDecidedBy() { return decidedBy; }

    // Setters
    public void setStatus(String status) { this.status = status; }
    public void setReason(String reason) { this.reason = reason; }
    public void setDecidedAt(LocalDateTime decidedAt) { this.decidedAt = decidedAt; }
    public void setDecidedBy(Long decidedBy) { this.decidedBy = decidedBy; }
}

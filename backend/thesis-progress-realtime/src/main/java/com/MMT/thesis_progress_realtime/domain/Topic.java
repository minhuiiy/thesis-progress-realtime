package com.MMT.thesis_progress_realtime.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity @Table(name="topics")
public class Topic {
    @Id 
    @GeneratedValue(strategy= GenerationType.IDENTITY) 
    private Long id;

    @Column(nullable=false) 
    private String title;

    @Column(columnDefinition="TEXT")
    private String description;

    @Column(name="lecturer_id", nullable=false) 
    private Long lecturerId;

    @Column(name="max_students", nullable=false)
    private int maxStudents = 1;

    @Column(nullable=false)
    private String status = "OPEN";

    @Column(name="created_at", nullable=false)
    private LocalDateTime createAt = LocalDateTime.now();

    // Getters
    public Long getId() { return id; }

    public String getTitle() { return title; }

    public String getDescription() { return description; }

    public Long getLecturerId() { return lecturerId; }

    public int getMaxStudents() { return maxStudents; }

    public String getStatus() { return status; }

    public LocalDateTime getCreateAt() { return createAt; }

    // Setters
    public void setTitle(String title) { this.title = title; }

    public void setDescription(String description) { this.description = description; }

    public void setLecturerId(Long lecturerId) { this.lecturerId = lecturerId; }

    public void setMaxStudents(int maxStudents) { this.maxStudents = maxStudents; }

    public void setStatus(String status) { this.status = status; }
}

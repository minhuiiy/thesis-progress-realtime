package com.MMT.thesis_progress_realtime.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MMT.thesis_progress_realtime.domain.TopicRegistration;

public interface TopicRegistrationRepo extends JpaRepository<TopicRegistration, Long> {
    List<TopicRegistration> findByStudentId(Long studentId);
    List<TopicRegistration> findByTopicId(Long topicId);
    Optional<TopicRegistration> findByTopicIdAndStudentId(Long topicId, Long studentId);
    boolean existsByStudentIdAndStatus(Long studentId, String status);
    long countByTopicIdAndStatus(Long topicId, String status);
}

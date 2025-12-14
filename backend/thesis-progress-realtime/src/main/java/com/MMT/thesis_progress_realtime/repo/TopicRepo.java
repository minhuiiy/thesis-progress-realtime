package com.MMT.thesis_progress_realtime.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MMT.thesis_progress_realtime.domain.Topic;


public interface TopicRepo extends JpaRepository<Topic, Long>{
    List<Topic> findByLecturerId(Long lecturerId);
}

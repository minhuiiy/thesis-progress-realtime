package com.MMT.thesis_progress_realtime.api;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.MMT.thesis_progress_realtime.domain.Topic;
import com.MMT.thesis_progress_realtime.repo.TopicRepo;
import com.MMT.thesis_progress_realtime.security.AuthPrincipal;

import jakarta.validation.constraints.NotBlank;




@RestController
public class TopicController {
    private final TopicRepo topicRepo;
    
    public TopicController(TopicRepo topicRepo) {
        this.topicRepo = topicRepo;
    }
    
    @GetMapping("/student/topics")
    public List<Topic> listForStudent() {
        return topicRepo.findAll();
    }

    public record CreateTopicReq(@NotBlank String title, String description, int maxStudents) {
    }

    @PostMapping("/lecturer/topics")
    public Topic createForLecturer(
        @AuthenticationPrincipal AuthPrincipal me,
        @RequestBody CreateTopicReq req
    ) {
        Topic t = new Topic();
        t.setTitle(req.title());
        t.setDescription(req.description());
        t.setMaxStudents(req.maxStudents() <= 0 ? 1 : req.maxStudents());
        t.setLecturerId(me.userId());
        return topicRepo.save(t);
    }
    
    @GetMapping("/lecturer/topics")
    public List<Topic> listForLecturer(@AuthenticationPrincipal AuthPrincipal me) {
        return topicRepo.findByLecturerId(me.userId());
    }
}

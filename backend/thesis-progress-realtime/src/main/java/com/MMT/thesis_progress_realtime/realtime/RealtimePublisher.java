package com.MMT.thesis_progress_realtime.realtime;

import java.util.Map;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class RealtimePublisher {
    private final SimpMessagingTemplate template;

    public RealtimePublisher(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void topicEvent(Long topicId, String event, Object data) {
        template.convertAndSend("/topic/topic." + topicId, (Object) Map.of("event", event, "data", data));
    }

    public void userNotify(Long userId, Object payload) {
        template.convertAndSendToUser(String.valueOf(userId), "/queue/notifications", payload);
    }
}

package com.example.kafkamongo.rest;

import com.example.kafkamongo.config.NotificationRequest;
import com.example.kafkamongo.services.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendNotification(@RequestBody NotificationRequest request){
        this.notificationService.sendNotification(request);
    }
}

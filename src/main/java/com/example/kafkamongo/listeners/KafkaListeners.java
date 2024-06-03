package com.example.kafkamongo.listeners;

import com.example.kafkamongo.entities.User;
import com.example.kafkamongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaListeners {

    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String mailFrom;

    private final UserService userService;

    public KafkaListeners(UserService userService) {
        this.userService = userService;
    }

    @KafkaListener(topics = "notification", groupId = "groupId")
    public void listener(String data) {
        List<User> users = this.userService.getAllUsers();
        users.forEach(u -> {
            if(u.getEmail() !=  null){
                this.sendMessage(u.getEmail(), "New notification", data);
            } else {
                System.out.println("No email found!");
            }
        });
    }

    private void sendMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(this.mailFrom);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        this.emailSender.send(message);
    }
}

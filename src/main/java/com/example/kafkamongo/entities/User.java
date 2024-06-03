package com.example.kafkamongo.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class User {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    @Indexed(unique = true)
    private String email;
    private String age;
    private LocalDateTime created;

}

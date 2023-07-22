package com.example.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user_entity")
public class User {

    @Id
    private String email;
    private String username;
    private String password;
}

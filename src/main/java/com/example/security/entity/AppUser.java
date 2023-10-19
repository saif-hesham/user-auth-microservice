package com.example.security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@Builder
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String email;
    private String password;
}

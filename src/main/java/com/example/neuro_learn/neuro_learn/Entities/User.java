package com.example.neuro_learn.neuro_learn.Entities;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotNull(message = "Name is required")
    private String username;

    @Column(name = "password")
    @NotNull(message = "Password is required")
    private String password;

    @Email(message = "Email should be valid")
    @Column(name = "email")
    @NotNull(message = "Email is required")
    private String email;

    @Column(name = "role")
    private String role;

    @NotNull(message = "Phone number is required")
    @Length(min = 10, max = 10, message = "Phone number must be 10 digits")
    @Column(name = "phone")
    private String phoneNumber;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Task> tasks = new ArrayList<>();

}

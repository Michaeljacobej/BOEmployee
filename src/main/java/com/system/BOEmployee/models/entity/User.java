package com.system.BOEmployee.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Data
@Table(name = "user", schema="public" )
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @UuidGenerator
    @Column(name = "ID")
    @JsonIgnore
    private UUID id;

    @Column(name = "FULLNAME")
    private String fullname;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;


    @Column(name = "CREATED_AT")
    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "UPDATED_AT")
    @UpdateTimestamp
    @JsonIgnore
    private LocalDateTime updatedAt;

}

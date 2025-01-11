package com.system.BOEmployee.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data

@Table(name = "EMPLOYEE", schema="public")
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @UuidGenerator
    @Column(name = "ID")
    @JsonIgnore
    private UUID id;

    @Column(name = "FULLNAME")
    private String fullname;

    @Column(name = "DOB")
    private Timestamp dob;

    @Column(name = "DEPARTMENT")
    private String department;

    @Column(name = "SALARY")
    private Long salary;

    @Column(name = "IS_ACTIVE")
    private String isActive;

    @Column(name = "CREATED_AT")
    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "UPDATED_AT")
    @UpdateTimestamp
    @JsonIgnore
    private LocalDateTime updatedAt;
}

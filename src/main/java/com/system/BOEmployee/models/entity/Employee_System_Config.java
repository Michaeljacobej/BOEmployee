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

@Table(name = "EMPLOYEE_SYSTEM_CONFIG", schema="public" )
@NoArgsConstructor
@AllArgsConstructor
public class Employee_System_Config {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    @JsonIgnore
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

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





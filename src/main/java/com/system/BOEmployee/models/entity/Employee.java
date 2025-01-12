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
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @UuidGenerator
//    @Column(name = "ID")
//    @JsonIgnore
//    private UUID id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    @JsonIgnore
    private Integer id;

//    @Column(name = "nmb", updatable = false, nullable = false)
//    private Integer nmb;
    @Column(name = "FULLNAME")
    private String fullname;

    @Column(name = "DOB")
    private Timestamp dob;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;


    @Column(name = "imgurl")
    private String imgurl;

    @Column(name = "SALARY")
    private Long salary;

    @Column(name = "DESCRIPTION")
    private String description;

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

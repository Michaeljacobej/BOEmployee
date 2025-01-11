package com.system.BOEmployee.models.dto.entity;

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
@Table(name = "Bank_Accounts", schema="public" )
@NoArgsConstructor
@AllArgsConstructor
public class Bank_Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @UuidGenerator
    @Column(name = "ID")
    @JsonIgnore
    private UUID id;

    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Column(name = "ACCOUNT_NAME")
    private String accountName;

    @Column(name = "NO_SEQUENCE")
    private String noSequence;


    @Column(name = "CURRENCY")
    private String currency;

    @Column(name = "IS_ACTIVE")
    private String isActive;

    @Column(name = "ACCT_TYPE")
    private String acctType;

    @Column(name = "CREATED_AT")
    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "UPDATED_AT")
    @UpdateTimestamp
    @JsonIgnore
    private LocalDateTime updatedAt;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "CHANNEL_MASTER_ID")
//    private Channel_Master channelMaster;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "CHANNEL_MASTER_ID")
//    private Channel_Master channelMaster;

}

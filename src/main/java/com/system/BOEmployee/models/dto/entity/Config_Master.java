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
@Table(name = "Config_Master", schema="public")
@NoArgsConstructor
@AllArgsConstructor
public class Config_Master {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @UuidGenerator
    @Column(name = "ID")
    @JsonIgnore
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "PARTNER_MASTER_ID")
    private Partner_Master partnerMaster;

    @Column(name = "FOLDER_CD")
    private String folderCd;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TEMPLATE_MASTER_ID")
    private Template_Master templateMaster;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BANK_ACCOUNTS_ID")
    private Bank_Accounts bankAccounts;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CHANNEL_MASTER_ID")
    private Channel_Master channelMaster;

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

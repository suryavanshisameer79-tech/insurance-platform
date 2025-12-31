package com.maximus.insurance.claim_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "claims")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Claim {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)    // Time-ordered UUIDs
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private UUID policyId;

    @Column(nullable = false)
    private Double claimAmount;

    @Column(nullable = false, length = 50)
    private String claimType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClaimStatus claimStatus;

    @Column(nullable = false)
    private LocalDateTime submittedDate;

    private LocalDateTime updatedDate;

    @Column(length = 500)
    private String description;

}

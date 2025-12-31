package com.maximus.insurance.claim_service.dto;

import com.maximus.insurance.claim_service.entity.ClaimStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClaimUpdateStatusRequestDto {

    @NotNull
    private ClaimStatus claimStatus;

    private String description;     //Optional — can store rejection reason
}

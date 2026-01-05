package com.maximus.insurance.claim_service.dto;

import com.maximus.insurance.claim_service.entity.ClaimStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

public record ClaimUpdateStatusRequestDto(

        @NotNull
        ClaimStatus claimStatus,

        String description     //Optional — can store rejection reason
) {

}

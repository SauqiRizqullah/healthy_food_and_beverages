package com.personal.healthyfoodandbeverages.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MembershipRequest {

    @Column(name = "membership_name")
    @Enumerated(EnumType.STRING)
    private String membershipName;

    @NotBlank(message = "If you are already part of membership, benefits are must thing you get")
    @Column(name = "benefit")
    private String benefit;
}

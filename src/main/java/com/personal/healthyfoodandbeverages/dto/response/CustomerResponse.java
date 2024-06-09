package com.personal.healthyfoodandbeverages.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CustomerResponse {
    private String customerId;
    private String customerName;
    private String mobilePhoneNo;
    private String membershipId;
    private Integer poin;
}

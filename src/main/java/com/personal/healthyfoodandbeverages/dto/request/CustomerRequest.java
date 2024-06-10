package com.personal.healthyfoodandbeverages.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequest {
    @NotBlank(message = "Person has a name")
    private String customerName;
    @NotBlank(message = "Person has a mobile phone number")
    @Pattern(regexp = "^08\\d{9,11}$", message = "Mobile phone number's pattern must be started by 08 and has 9 until 12 digits")
    private String mobilePhoneNo;
    private String membershipId;
//    private Integer poin;
}

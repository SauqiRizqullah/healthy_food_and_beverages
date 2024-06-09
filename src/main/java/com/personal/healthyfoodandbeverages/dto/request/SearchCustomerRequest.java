package com.personal.healthyfoodandbeverages.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchCustomerRequest {
    private String customerName;
    private String mobilePhoneNo;
    private String membershipId;
    private Integer poin;
}

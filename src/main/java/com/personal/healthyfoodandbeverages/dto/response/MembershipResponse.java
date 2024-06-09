package com.personal.healthyfoodandbeverages.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class MembershipResponse {
    private String membershipId;
    private String membershipName;
    private String benefit;
}

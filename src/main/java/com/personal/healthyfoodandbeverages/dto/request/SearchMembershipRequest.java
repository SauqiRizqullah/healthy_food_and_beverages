package com.personal.healthyfoodandbeverages.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchMembershipRequest {
    private String membershipName;
    private String benefit;
}

package com.personal.healthyfoodandbeverages.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchBonusRequest {
    private String bonusName;
    private Integer poin;
}

package com.personal.healthyfoodandbeverages.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BonusRequest {
    private String bonusName;
    private Integer poin;
}

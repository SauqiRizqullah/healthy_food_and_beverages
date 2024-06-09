package com.personal.healthyfoodandbeverages.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class BonusResponse {
    private String bonusId;
    private String bonusName;
    private Integer poin;
}

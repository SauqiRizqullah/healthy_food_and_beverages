package com.personal.healthyfoodandbeverages.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDetailRequest {
    private String menuId;
    private Integer qty;
}

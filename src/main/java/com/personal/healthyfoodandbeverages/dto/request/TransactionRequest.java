package com.personal.healthyfoodandbeverages.dto.request;

import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionRequest {
    private String customerId;
//    private String membershipName;
    private String bonusId;
    private List<TransactionDetailRequest> transactionDetails;
//    private Date transDate;
}

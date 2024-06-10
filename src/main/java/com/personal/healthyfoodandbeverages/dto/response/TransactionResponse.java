package com.personal.healthyfoodandbeverages.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Builder
public class TransactionResponse {
    private String trxId;
    private String customerId;
    private String membershipName;
    private String bonusId;
    private List<TransactionDetailResponse> transactionDetails;
    private Date transDate;
}

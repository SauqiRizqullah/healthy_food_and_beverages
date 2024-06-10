package com.personal.healthyfoodandbeverages.service;

import com.personal.healthyfoodandbeverages.entity.TransactionDetail;

import java.util.List;

public interface TransactionDetailService {
    List<TransactionDetail> createBulk(List<TransactionDetail> transactionDetail);
}

package com.personal.healthyfoodandbeverages.service;

import com.personal.healthyfoodandbeverages.dto.request.TransactionRequest;
import com.personal.healthyfoodandbeverages.dto.response.TransactionResponse;

import java.util.List;

public interface TransactionService {

    TransactionResponse createNewTransaction (TransactionRequest transactionRequest);

    List<TransactionResponse> getAllTransactions();

}

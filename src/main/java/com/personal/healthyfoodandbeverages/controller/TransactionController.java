package com.personal.healthyfoodandbeverages.controller;

import com.personal.healthyfoodandbeverages.constant.APIUrl;
import com.personal.healthyfoodandbeverages.dto.request.TransactionRequest;
import com.personal.healthyfoodandbeverages.dto.response.TransactionResponse;
import com.personal.healthyfoodandbeverages.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.TRANSACTION)
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping(produces = "application/json")
    public TransactionResponse createNewTransaction (
            @RequestBody TransactionRequest transactionRequest
            ){
        return transactionService.createNewTransaction(transactionRequest);
    }

    @GetMapping(produces = "application/json")
    public List<TransactionResponse> getAllTransactions (){
        return transactionService.getAllTransactions();
    }

}

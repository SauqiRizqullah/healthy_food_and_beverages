package com.personal.healthyfoodandbeverages.service.impl;

import com.personal.healthyfoodandbeverages.entity.TransactionDetail;
import com.personal.healthyfoodandbeverages.repository.TransactionDetailRepository;
import com.personal.healthyfoodandbeverages.service.TransactionDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionDetailServiceImpl implements TransactionDetailService {

    private final TransactionDetailRepository transactionDetailRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<TransactionDetail> createBulk(List<TransactionDetail> transactionDetail) {
        return transactionDetailRepository.saveAllAndFlush(transactionDetail);
    }
}


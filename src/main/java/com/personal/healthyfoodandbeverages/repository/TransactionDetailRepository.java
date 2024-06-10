package com.personal.healthyfoodandbeverages.repository;

import com.personal.healthyfoodandbeverages.entity.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, String> {
}

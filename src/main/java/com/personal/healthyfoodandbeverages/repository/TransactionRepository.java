package com.personal.healthyfoodandbeverages.repository;

import com.personal.healthyfoodandbeverages.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
}

package com.personal.healthyfoodandbeverages.repository;

import com.personal.healthyfoodandbeverages.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, String>, JpaSpecificationExecutor<Customer> {
    @Modifying
    @Query(value = "UPDATE m_customer SET membership_id = :membershipId WHERE customer_id = :customerId", nativeQuery = true)
    void updateMembershipById (@Param("customerId") String customerId, @Param("membershipId") String newMembershipId);

    @Modifying
    @Query(value = "UPDATE m_customer SET poin = :poinUp WHERE customer_id = :customerId", nativeQuery = true)
    void updatePoinById(@Param("customerId") String customerId, @Param("poinUp") Integer poinUp);
}

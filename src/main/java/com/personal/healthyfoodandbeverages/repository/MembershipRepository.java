package com.personal.healthyfoodandbeverages.repository;

import com.personal.healthyfoodandbeverages.entity.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface MembershipRepository extends JpaRepository<Membership, String>, JpaSpecificationExecutor<Membership> {

    @Modifying
    @Query(value = "UPDATE m_membership SET benefit = :benefit WHERE membership_id = :membershipId", nativeQuery = true)
    void updateBenefitById (@Param("benefit") String newBenefit, @Param("membershipId") String membershipId);


}

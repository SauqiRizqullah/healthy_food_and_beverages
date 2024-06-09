package com.personal.healthyfoodandbeverages.repository;

import com.personal.healthyfoodandbeverages.entity.Bonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BonusRepository extends JpaRepository<Bonus, String>, JpaSpecificationExecutor<Bonus> {

    @Modifying
    @Query(value = "UPDATE m_bonus SET poin = :poin WHERE bonus_id = :bonusId", nativeQuery = true)
    void updatePoinById(@Param("poin") Integer poin, @Param("bonusId") String bonusId);
}

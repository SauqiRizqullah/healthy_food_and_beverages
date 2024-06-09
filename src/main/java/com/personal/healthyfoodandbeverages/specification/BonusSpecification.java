package com.personal.healthyfoodandbeverages.specification;

import com.personal.healthyfoodandbeverages.dto.request.SearchBonusRequest;
import com.personal.healthyfoodandbeverages.entity.Bonus;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BonusSpecification {
    public static Specification<Bonus> getSpecification (SearchBonusRequest bonusRequest){
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            if (bonusRequest.getBonusName() != null) {
                Predicate bonusNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("bonusName")), "%" + bonusRequest.getBonusName().toLowerCase() + "%");
                predicates.add(bonusNamePredicate);
            }

            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
    }
}

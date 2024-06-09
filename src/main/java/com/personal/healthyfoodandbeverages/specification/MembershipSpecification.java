package com.personal.healthyfoodandbeverages.specification;

import com.personal.healthyfoodandbeverages.dto.request.SearchMembershipRequest;
import com.personal.healthyfoodandbeverages.entity.Membership;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class MembershipSpecification {
    public static Specification<Membership> getSpecification (SearchMembershipRequest request){
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            if (request.getMembershipName() != null) {
                Predicate membershipNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("membershipName")), "%" + request.getMembershipName().toLowerCase() + "%");
                predicates.add(membershipNamePredicate);
            }

            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
    }
}

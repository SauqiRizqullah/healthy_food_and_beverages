package com.personal.healthyfoodandbeverages.service.impl;

import com.personal.healthyfoodandbeverages.constant.MembershipName;
import com.personal.healthyfoodandbeverages.dto.request.MembershipRequest;
import com.personal.healthyfoodandbeverages.dto.request.SearchMembershipRequest;
import com.personal.healthyfoodandbeverages.dto.response.MembershipResponse;
import com.personal.healthyfoodandbeverages.entity.Bonus;
import com.personal.healthyfoodandbeverages.entity.Membership;
import com.personal.healthyfoodandbeverages.repository.MembershipRepository;
import com.personal.healthyfoodandbeverages.service.MembershipService;
import com.personal.healthyfoodandbeverages.specification.MembershipSpecification;
import com.personal.healthyfoodandbeverages.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MembershipServiceImpl implements MembershipService {

    private final MembershipRepository membershipRepository;

    private final ValidationUtil validationUtil;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public MembershipResponse createNewMemberShip(MembershipRequest membershipRequest) {
        validationUtil.validate(membershipRequest);
        // 1. Membuat objeknya

        MembershipName membershipName;
        if (membershipRequest.getMembershipName().equalsIgnoreCase("silver")) {
//            membershipRequest.setMembershipName("SILVER");
            membershipName = MembershipName.SILVER;
        } else if (membershipRequest.getMembershipName().equalsIgnoreCase("gold")) {
//            membershipRequest.setMembershipName("GOLD");
            membershipName = MembershipName.GOLD;
        } else if (membershipRequest.getMembershipName().equalsIgnoreCase("none")){
//            membershipRequest.setMembershipName("NONE");
            membershipName = MembershipName.NONE;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Repeat the process from the beginning");
        }

        Membership newMembership;

        if (membershipName == MembershipName.GOLD){
            newMembership = Membership.builder()
                    .membershipName(membershipName)
                    .benefit(membershipRequest.getBenefit() + " + bonus dari membership Silver")
                    .build();
        } else {
            newMembership = Membership.builder()
                    .membershipName(membershipName)
                    .benefit(membershipRequest.getBenefit())
                    .build();
        }


        membershipRepository.saveAndFlush(newMembership);

        return parseMembershipToMembershipResponse(newMembership);
    }

    private MembershipResponse parseMembershipToMembershipResponse(Membership membership) {
        String id;
        if (membership.getMembershipId() == null){
            id = null;
        } else {
            id = membership.getMembershipId();
        }

        return MembershipResponse.builder()
                .membershipId(id)
                .membershipName(membership.getMembershipName().toString())
                .benefit(membership.getBenefit())
                .build();

    }

    @Transactional(readOnly = true)
    @Override
    public Membership getMembershipbyId(String membershipId) {

        return membershipRepository.findById(membershipId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id was not there"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<MembershipResponse> getAllMemberships(SearchMembershipRequest membershipRequest) {
        Specification<Membership> membershipSpecification = MembershipSpecification.getSpecification(membershipRequest);
        if (membershipRequest.getMembershipName() == null){
            return membershipRepository.findAll().stream().map(this::parseMembershipToMembershipResponse).toList();
        } else
            return membershipRepository.findAll(membershipSpecification).stream().map(this::parseMembershipToMembershipResponse).toList();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String updateBenefitById(String membershipId, String newBenefit) {


        Membership membership = membershipRepository.findById(membershipId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id was not there"));

        membership.setBenefit(newBenefit);

        validationUtil.validate(membership);

        membershipRepository.updateBenefitById(newBenefit, membershipId);

        membershipRepository.saveAndFlush(membership);
        return "Therefore the benefit is " + newBenefit;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public MembershipResponse deleteById(String membershipId) {
        Membership membership = membershipRepository.findById(membershipId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id was not there"));

        membershipRepository.delete(membership);
        return parseMembershipToMembershipResponse(membership);
    }
}

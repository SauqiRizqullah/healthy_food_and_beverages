package com.personal.healthyfoodandbeverages.service;

import com.personal.healthyfoodandbeverages.dto.request.MembershipRequest;
import com.personal.healthyfoodandbeverages.dto.request.SearchMembershipRequest;
import com.personal.healthyfoodandbeverages.dto.response.MembershipResponse;
import com.personal.healthyfoodandbeverages.entity.Membership;

import java.util.List;

public interface MembershipService {
    MembershipResponse createNewMemberShip (MembershipRequest membershipRequest);

    Membership getMembershipbyId (String membershipId);

    List<MembershipResponse> getAllMemberships (SearchMembershipRequest membershipRequest);

    String updateBenefitById (String membershipId, String newBenefit);

    MembershipResponse deleteById (String membershipId);
}

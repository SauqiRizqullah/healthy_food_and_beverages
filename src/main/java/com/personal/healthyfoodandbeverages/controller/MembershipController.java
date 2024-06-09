package com.personal.healthyfoodandbeverages.controller;

import com.personal.healthyfoodandbeverages.constant.APIUrl;
import com.personal.healthyfoodandbeverages.dto.request.MembershipRequest;
import com.personal.healthyfoodandbeverages.dto.request.SearchMembershipRequest;
import com.personal.healthyfoodandbeverages.dto.response.CommonResponse;
import com.personal.healthyfoodandbeverages.dto.response.MembershipResponse;
import com.personal.healthyfoodandbeverages.entity.Membership;
import com.personal.healthyfoodandbeverages.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.MEMBERSHIP)
public class MembershipController {
    private final MembershipService membershipService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<CommonResponse<MembershipResponse>> createNewMembership (
            @RequestBody MembershipRequest membershipRequest
            ){
        // 1. Memanggil service untuk melakukan pembuatan membership baru
        MembershipResponse newMemberShip = membershipService.createNewMemberShip(membershipRequest);

        CommonResponse<MembershipResponse> response = CommonResponse.<MembershipResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Data of Membership has been added")
                .data(newMemberShip)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = APIUrl.PATH_VAR_MEMBERSHIP_ID, produces = "application/json")
    public ResponseEntity<CommonResponse<Membership>> getMembershipById (
            @PathVariable String membershipId
    ) {
        Membership membership = membershipService.getMembershipbyId(membershipId);

        CommonResponse<Membership> response = CommonResponse.<Membership>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Here's the data you wanted to see")
                .data(membership)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<CommonResponse<List<MembershipResponse>>> getAllMemberships(
            @RequestParam(name = "membershipName", required = false) String membershipName
    ) {
        SearchMembershipRequest membershipRequest = SearchMembershipRequest.builder()
                .membershipName(membershipName)
                .build();

        List<MembershipResponse> allMemberships = membershipService.getAllMemberships(membershipRequest);

        CommonResponse<List<MembershipResponse> > response = CommonResponse.<List<MembershipResponse> >builder()
                .statusCode(HttpStatus.OK.value())
                .message("Here's the data you wanted to see")
                .data(allMemberships)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = APIUrl.PATH_VAR_MEMBERSHIP_ID, produces = "application/json")
    public ResponseEntity<CommonResponse<String>> updateBenefitById (
            @PathVariable String membershipId,
            @RequestParam(name = "benefit") String newBenefit
    ) {
        String membership = membershipService.updateBenefitById(membershipId, newBenefit);

        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Here's the data you wanted to see")
                .data(membership)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = APIUrl.PATH_VAR_MEMBERSHIP_ID,produces = "application/json")
    public ResponseEntity<CommonResponse<MembershipResponse>> deleteById (
            @PathVariable String membershipId
    ) {
        MembershipResponse membershipResponse = membershipService.deleteById(membershipId);

        CommonResponse<MembershipResponse> response = CommonResponse.<MembershipResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(membershipId + "'s data has been deleted")
                .data(membershipResponse)
                .build();

        return ResponseEntity.ok(response);
    }
    // LANJUTKAN YANG DELETE

}

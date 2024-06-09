package com.personal.healthyfoodandbeverages.controller;

import com.personal.healthyfoodandbeverages.constant.APIUrl;
import com.personal.healthyfoodandbeverages.dto.request.BonusRequest;
import com.personal.healthyfoodandbeverages.dto.request.SearchBonusRequest;
import com.personal.healthyfoodandbeverages.dto.response.BonusResponse;
import com.personal.healthyfoodandbeverages.dto.response.CommonResponse;
import com.personal.healthyfoodandbeverages.entity.Bonus;
import com.personal.healthyfoodandbeverages.service.BonusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIUrl.BONUS)
public class BonusController {

    private final BonusService bonusService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<CommonResponse<BonusResponse>> createNewTransTypeV2(
            @RequestBody BonusRequest bonusRequest
    ) {
        BonusResponse bonusResponse = bonusService.createNewBonus(bonusRequest);

        CommonResponse<BonusResponse> response = CommonResponse.<BonusResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("New bonus has been added")
                .data(bonusResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = APIUrl.PATH_VAR_BONUS_ID, produces = "application/json")
    public ResponseEntity<CommonResponse<Bonus>> getBonusById (
            @PathVariable String bonusId
    ) {
        // 1. Memanggil objek dengan Idnya
        Bonus bonus = bonusService.getBonusById(bonusId);

        CommonResponse<Bonus> response = CommonResponse.<Bonus>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Here is your request")
                .data(bonus)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<CommonResponse<List<BonusResponse>>> getAllTransTypesV2 (
            @RequestParam(name = "bonusName", required = false) String bonusName
    ) {
//        SearchTransTypeRequest request = SearchTransTypeRequest.builder()
//                .description(description)
//                .build();

        SearchBonusRequest request = SearchBonusRequest.builder()
                .bonusName(bonusName)
                .build();

        List<BonusResponse> allBonuses = bonusService.getAllBonuses(request);

        CommonResponse<List<BonusResponse>> response = CommonResponse.<List<BonusResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Here's your request")
                .data(allBonuses)
                .build();
        return ResponseEntity.ok(response);
    }
    @PutMapping(path = APIUrl.PATH_VAR_BONUS_ID, produces = "application/json")
    public ResponseEntity<CommonResponse<String>> updateBonusById(
            @RequestParam(name = "poin") Integer newPoin,
            @PathVariable String bonusId
    ) {
        String dataUpdate = bonusService.updatePoinById(bonusId, newPoin);

        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message(bonusId + "'s description has been updated a second ago")
                .data(dataUpdate)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = APIUrl.PATH_VAR_BONUS_ID, produces = "application/json")
    public ResponseEntity<CommonResponse<String>> deleteBonusById (
            @PathVariable String bonusId
    ) {
        String dataDelete = bonusService.deleteBonusById(bonusId);

        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message(bonusId + "'s data has been removed from database")
                .data(dataDelete)
                .build();

        return ResponseEntity.ok(response);
    }
}

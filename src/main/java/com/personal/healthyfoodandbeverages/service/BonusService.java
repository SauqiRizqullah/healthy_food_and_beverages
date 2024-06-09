package com.personal.healthyfoodandbeverages.service;

import com.personal.healthyfoodandbeverages.dto.request.BonusRequest;
import com.personal.healthyfoodandbeverages.dto.request.SearchBonusRequest;
import com.personal.healthyfoodandbeverages.dto.response.BonusResponse;
import com.personal.healthyfoodandbeverages.entity.Bonus;

import java.util.List;

public interface BonusService {
    BonusResponse createNewBonus (BonusRequest bonusRequest);

    Bonus getBonusById (String bonusId);

    List<BonusResponse> getAllBonuses (SearchBonusRequest bonusRequest);

    String updatePoinById (String bonusId, Integer poin);

    String deleteBonusById (String bonusId);


}

/*
        SERVICE FORMAT
    1. Create       Output: Response                            Input: NewRequest
    2. SearchbyID   Output: Response                            Input: String Id
    3. SearchAll    Output: List<Response> / Page<Entity>       Input: SearchRequest
    4. UpdatebyId   Output: String                              Input: String Id / Nilai lainnya
    5. DeletebyId   Output: String                              Input: String Id

        CONTROLLER FORMAT
    1. Create       Output: ResponseEntity<CommonResponse<Response>>        Input: @RequestBody NewRequest
    2. SearchbyID   Output: ResponseEntity<CommonResponse<Response>>        Input: @PathVariable String Id
    3. SearchAll    Output: ResponseEntity<CommonResponse<List<Response>>>  Input: @RequestParam Page / {Kolom lain dari entity itu}
    4. UpdatebyId   Output: ResponseEntity<CommonResponse<String>>          Input: @PathVariable String Id, @RequestParam nilaiYangMauDiubah
    5. DeletebyId   Output: ResponseEntity<CommonResponse<String>>          Input: @PathVariable String Id

     */

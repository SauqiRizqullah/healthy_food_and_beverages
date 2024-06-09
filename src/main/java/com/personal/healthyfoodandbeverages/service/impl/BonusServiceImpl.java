package com.personal.healthyfoodandbeverages.service.impl;

import com.personal.healthyfoodandbeverages.dto.request.BonusRequest;
import com.personal.healthyfoodandbeverages.dto.request.SearchBonusRequest;
import com.personal.healthyfoodandbeverages.dto.response.BonusResponse;
import com.personal.healthyfoodandbeverages.entity.Bonus;
import com.personal.healthyfoodandbeverages.repository.BonusRepository;
import com.personal.healthyfoodandbeverages.service.BonusService;
import com.personal.healthyfoodandbeverages.specification.BonusSpecification;
import com.personal.healthyfoodandbeverages.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BonusServiceImpl implements BonusService {

    private final BonusRepository bonusRepository;

    private final ValidationUtil validationUtil;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BonusResponse createNewBonus(BonusRequest bonusRequest) {
        validationUtil.validate(bonusRequest);
        Bonus bonus = Bonus.builder()
                .bonusName(bonusRequest.getBonusName())
                .poin(bonusRequest.getPoin())
                .build();

        bonusRepository.saveAndFlush(bonus);

        return parseBonusToBonusResponse(bonus);
    }

    private BonusResponse parseBonusToBonusResponse(Bonus bonus) {
        String id;
        if (bonus.getBonusId() == null) {
            id = null;
        } else {
            id = bonus.getBonusId();
        }

        return BonusResponse.builder()
                .bonusId(id)
                .bonusName(bonus.getBonusName())
                .poin(bonus.getPoin())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public Bonus getBonusById(String bonusId) {
        return bonusRepository.findById(bonusId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id was not there"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<BonusResponse> getAllBonuses(SearchBonusRequest bonusRequest) {
        // 1. Menambahkan specification dalam melakukan query
        Specification<Bonus> bonusSpecification = BonusSpecification.getSpecification(bonusRequest);
        // 2. Jika tidak memasukkan specification untuk melakukan query, maka query normal saja, tapi di parse dulu ke responsenya
        if (bonusRequest.getBonusName() == null){
            return bonusRepository.findAll().stream().map(this::parseBonusToBonusResponse).toList();
        } else
            // 3.
            return bonusRepository.findAll(bonusSpecification).stream().map(this::parseBonusToBonusResponse).toList();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String updatePoinById(String bonusId, Integer poin) {
        //1. Mencari objek dengan transTypeId dengan metode orElseThrow
        Bonus bonus = bonusRepository.findById(bonusId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id was not there"));

        //2. Memperbarui nilai deskripsi dengan setter
        bonus.setPoin(poin);

        validationUtil.validate(bonus);

        //3. Jangan lupa lakukan query native dan melakukan penyimpanan data di database (saveAndFlush) melalui repository
        bonusRepository.updatePoinById(poin, bonusId);
        bonusRepository.saveAndFlush(bonus);

        return "Therefore, the new poin of this bonus is " + poin + " point's";
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String deleteBonusById(String bonusId) {
        Bonus bonus = bonusRepository.findById(bonusId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id was not there"));

        bonusRepository.delete(bonus);
        return "Therefore, you must choose another bonuses";
    }
}

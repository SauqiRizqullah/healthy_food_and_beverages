package com.personal.healthyfoodandbeverages.service.impl;

import com.personal.healthyfoodandbeverages.constant.MembershipName;
import com.personal.healthyfoodandbeverages.dto.request.TransactionRequest;
import com.personal.healthyfoodandbeverages.dto.response.TransactionDetailResponse;
import com.personal.healthyfoodandbeverages.dto.response.TransactionResponse;
import com.personal.healthyfoodandbeverages.entity.*;
import com.personal.healthyfoodandbeverages.repository.TransactionRepository;
import com.personal.healthyfoodandbeverages.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final CustomerService customerService;

    private final MenuService menuService;

    private final BonusService bonusService;

    private final TransactionDetailService transactionDetailService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TransactionResponse createNewTransaction(TransactionRequest transactionRequest) {

        // Perhatikan kebutuhan tabel transaksi, maka kita bisa menentukan objeknya
        Customer customer = customerService.getById(transactionRequest.getCustomerId());

        Bonus bonus = bonusService.getBonusById(transactionRequest.getBonusId());

        // Pembuatan Objek Transaksi

        Transaction trx = Transaction.builder()
                .customer(customer)
                .membershipName(customer.getMembership().getMembershipName().toString())
                .bonus(bonus)
                .transDate(new Date())
                .build();

        if (trx.getCustomer().getPoin() < trx.getBonus().getPoin() || trx.getBonus() == null) {
            Bonus noBonus = bonusService.getNoBonus(MembershipName.NONE.toString());
            trx.setBonus(noBonus);
        } else {
            customer.setPoin(trx.getCustomer().getPoin() - trx.getBonus().getPoin());
        }

        // Menambah poin layaknya dengan save and flush ke repo customer, tp harus lewat service dulu

//        customer.setPoin(customer.getPoin()+1);

        if (!Objects.equals(trx.getMembershipName(), MembershipName.NONE.toString())){
            customerService.updatePoinById(customer.getCustomerId());
        }

        // Simpan Transaksi dalam database

        Transaction savedTransaction = transactionRepository.save(trx);

        // Membuat daftar detail dari transaksi

        List<TransactionDetail> trxDetail = transactionRequest.getTransactionDetails().stream()
                .map(detailRequest -> {

                    // Informasi quantity pada terminal Intellij

                    log.info("Quantity from transaction detail request: {}", detailRequest.getQty());

                    // Pemilihan Menu

                    Menu menu = menuService.getMenuById(detailRequest.getMenuId());

                    return TransactionDetail.builder()
                            .transaction(savedTransaction)
                            .menu(menu)
                            .menuPrice(menu.getMenuPrice())
                            .qty(detailRequest.getQty())
                            .build();

                }).toList();

        // Memanggil service detail transaksi untuk disimpan di database

        transactionDetailService.createBulk(trxDetail);
        savedTransaction.setTransactionDetails(trxDetail);

        // Membuat respons dari transaksi detail dengan dikumpulin di metode stream map

        List<TransactionDetailResponse> trxDetailResponse = trxDetail.stream().map(
                detail -> {
                    return TransactionDetailResponse.builder()
                            .trxDetailId(detail.getTrxDetailId())
                            .menuId(detail.getMenu().getMenuId())
                            .menuPrice(detail.getMenu().getMenuPrice())
                            .qty(detail.getQty())
                            .build();
                }).toList();


        return TransactionResponse.builder()
                .trxId(savedTransaction.getTrxId())
                .customerId(savedTransaction.getCustomer().getCustomerId())
                .bonusId(savedTransaction.getBonus().getBonusId())
                .membershipName(savedTransaction.getCustomer().getMembership().getMembershipName().toString())
                .transDate(savedTransaction.getTransDate())
                .transactionDetails(trxDetailResponse)
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public List<TransactionResponse> getAllTransactions() {

        // Memanggil repository findAll terlebih dahulu
        List<Transaction> transactions = transactionRepository.findAll();



        return transactions.stream().map( trx -> {
            List<TransactionDetailResponse> trxDetailResponse = trx.getTransactionDetails().stream().map( trxDetail -> {
            return TransactionDetailResponse.builder()
                    .trxDetailId(trxDetail.getTrxDetailId())
                    .menuId(trxDetail.getMenu().getMenuId())
                    .menuPrice(trxDetail.getMenu().getMenuPrice())
                    .qty(trxDetail.getQty())
                    .build();
            }).toList();

        return TransactionResponse.builder()
                .trxId(trx.getTrxId())
                .customerId(trx.getCustomer().getCustomerId())
                .bonusId(trx.getBonus().getBonusId())
                .membershipName(trx.getCustomer().getMembership().getMembershipName().toString())
                .transDate(trx.getTransDate())
                .transactionDetails(trxDetailResponse)
                .build();
        }).toList();



    }
}

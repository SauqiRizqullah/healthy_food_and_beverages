package com.personal.healthyfoodandbeverages.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.personal.healthyfoodandbeverages.constant.ConstantTable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = ConstantTable.TRANSACTION_DETAIL)
public class TransactionDetail {
    @Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "com.personal.healthyfoodandbeverages.utils.TransactionDetailCustomId")
    @Column(name = "trx_detail_id")
    private String trxDetailId;

    @ManyToOne
    @JoinColumn(name = "trx_id", nullable = false)
    @JsonBackReference
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "menu_id",nullable = false)
    private Menu menu;

    @Column(name = "menu_price",nullable = false, updatable = false)
    private Long menuPrice;

    @Column(name = "qty",nullable = false)
    private Integer qty;
}

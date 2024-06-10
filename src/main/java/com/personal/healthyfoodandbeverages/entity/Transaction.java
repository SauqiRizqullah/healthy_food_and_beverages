package com.personal.healthyfoodandbeverages.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.personal.healthyfoodandbeverages.constant.ConstantTable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = ConstantTable.TRANSACTION)
public class Transaction {
    @Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "com.personal.healthyfoodandbeverages.utils.TransactionCustomId")
    @Column(name = "trx_id")
    private String trxId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Column(name = "membership_name")
    private String membershipName;

    @ManyToOne
    @JoinColumn(name = "bonus_id")
    private Bonus bonus;

    @OneToMany(mappedBy = "transaction")
    @JsonManagedReference
    private List<TransactionDetail> transactionDetails;

    @Temporal(TemporalType.DATE)
    @Column(name = "trans_date", updatable = false)
    private Date transDate;
}

//PR
//Lanjutkan repository transaction hingga controllernya teruji semua
//Readme.md
//Security JWT selesai

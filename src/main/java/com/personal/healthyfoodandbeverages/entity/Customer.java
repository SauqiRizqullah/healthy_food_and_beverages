package com.personal.healthyfoodandbeverages.entity;

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
@Table(name = ConstantTable.CUSTOMER)
public class Customer {
    @Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "com.personal.healthyfoodandbeverages.utils.CustomerCustomId")
    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "customer_name", unique = true)
    private String customerName;

    @Column(name = "mobile_phone_no", unique = true)
    private String mobilePhoneNo;

    @ManyToOne
    @JoinColumn(name = "membership_id")
    private Membership membership;

    @Column(name = "poin")
    private Integer poin;
}

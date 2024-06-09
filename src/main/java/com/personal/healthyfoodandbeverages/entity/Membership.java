package com.personal.healthyfoodandbeverages.entity;

import com.personal.healthyfoodandbeverages.constant.ConstantTable;
import com.personal.healthyfoodandbeverages.constant.MembershipName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = ConstantTable.MEMBERSHIP)
public class Membership {
    @Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "com.personal.healthyfoodandbeverages.utils.MembershipCustomId")
    @Column(name = "membership_id")
    private String membershipId;

    @Column(name = "membership_name")
    @Enumerated(EnumType.STRING)
    private MembershipName membershipName;

    @NotBlank(message = "If you are already part of membership, benefits are must thing you get")
    @Column(name = "benefit")
    private String benefit;


}

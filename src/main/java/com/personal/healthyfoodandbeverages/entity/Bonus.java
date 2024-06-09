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
@Table(name = ConstantTable.BONUS)
public class Bonus {
    @Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "com.personal.healthyfoodandbeverages.utils.BonusCustomId")
    @Column(name = "bonus_id")
    private String bonusId;

    @Column(name = "bonus_name")
    private String bonusName;

    @Column(name = "poin")
    private Integer poin;
}

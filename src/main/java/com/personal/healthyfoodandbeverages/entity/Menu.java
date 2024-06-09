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
@Table(name = ConstantTable.MENU)
public class Menu {
    @Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "com.personal.healthyfoodandbeverages.utils.MenuCustomId")
    @Column(name = "menu_id")
    private String menuId;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private Long menuPrice;
}

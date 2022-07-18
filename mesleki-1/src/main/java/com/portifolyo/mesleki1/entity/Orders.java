package com.portifolyo.mesleki1.entity;

import com.portifolyo.mesleki1.enums.OrderStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@EqualsAndHashCode(callSuper = true)
public class Orders extends BaseEntity {


    private BigDecimal price;
    @OneToOne(cascade = CascadeType.ALL)
    private Adress adress;
    @OneToOne
    private Product product;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

}

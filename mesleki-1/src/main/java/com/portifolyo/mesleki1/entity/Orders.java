package com.portifolyo.mesleki1.entity;

import com.portifolyo.mesleki1.enums.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
@Entity

public class Orders extends BaseEntity {


    private BigDecimal price;
    @OneToOne(cascade = CascadeType.ALL)
    private Adress adress;
    @OneToOne
    private Product product;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

}

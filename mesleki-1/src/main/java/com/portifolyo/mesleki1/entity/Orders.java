package com.portifolyo.mesleki1.entity;

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
    @OneToOne
    private Adress adress;
    @OneToOne
    private Product product;

}

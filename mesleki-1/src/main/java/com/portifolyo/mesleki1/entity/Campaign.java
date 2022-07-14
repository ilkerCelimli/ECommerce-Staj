package com.portifolyo.mesleki1.entity;

import com.portifolyo.mesleki1.entity.BaseEntity;
import com.portifolyo.mesleki1.entity.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class Campaign extends BaseEntity {

    private String code;
    private String description;
    private double discountRate;

    @OneToOne
    public Product product;
}

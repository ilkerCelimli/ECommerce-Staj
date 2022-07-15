package com.portifolyo.mesleki1.entity;

import com.portifolyo.mesleki1.entity.BaseEntity;
import com.portifolyo.mesleki1.entity.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Campaign extends BaseEntity {

    @Column
    private String code;
    @Column
    private String description;
    @Column
    private double discountRate;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @OneToOne
    public Product product;
}

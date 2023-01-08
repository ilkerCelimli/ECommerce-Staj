package com.portifolyo.mesleki1.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Entity
@Data
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

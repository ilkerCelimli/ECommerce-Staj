package com.portifolyo.mesleki1.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Shopper extends BaseEntity{

    private String name;
    private long taxNumber;
    @OneToOne(fetch = FetchType.EAGER)
    private User user;


}

package com.portifolyo.mesleki1.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import java.math.BigDecimal;

@Entity
@Inheritance
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseEntity{


    private String name;
    private String description;
    private BigDecimal price;


}

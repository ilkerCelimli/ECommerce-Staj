package com.portifolyo.mesleki1.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Inheritance
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Product extends BaseEntity{


    private String name;
    private String description;
    private BigDecimal price;
    @ManyToOne(fetch = FetchType.EAGER)
    private Categories categories;
    @ManyToOne(fetch = FetchType.EAGER)
    private Shopper shopper;


    public Product(String name,String description,BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

}

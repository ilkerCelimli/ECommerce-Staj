package com.portifolyo.mesleki1.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "shoppers")
@Table(name = "shoppers")
@EqualsAndHashCode(callSuper = true)
public class Shopper extends BaseEntity{

    private String name;
    private long taxNumber;
    @OneToOne(fetch = FetchType.EAGER)
    private User user;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Adress adress;
    @OneToMany(mappedBy = "shopper",fetch = FetchType.EAGER)
    private List<Product> productList;


}

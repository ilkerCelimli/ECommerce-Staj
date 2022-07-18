package com.portifolyo.mesleki1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

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


}

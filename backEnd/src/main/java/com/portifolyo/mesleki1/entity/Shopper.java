package com.portifolyo.mesleki1.entity;

import lombok.*;
import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity(name = "shoppers")
@Table(name = "shoppers")
public class Shopper extends BaseEntity{

    private String name;
    private long taxNumber;
    @OneToOne(fetch = FetchType.EAGER)
    private User user;


}

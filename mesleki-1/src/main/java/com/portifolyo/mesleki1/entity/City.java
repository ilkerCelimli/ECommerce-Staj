package com.portifolyo.mesleki1.entity;

import lombok.*;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class City extends BaseEntity {

    private String code;
    private String city;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Country country;

}

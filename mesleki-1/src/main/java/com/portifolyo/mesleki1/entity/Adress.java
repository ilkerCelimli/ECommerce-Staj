package com.portifolyo.mesleki1.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(callSuper = true)
public class Adress extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL)
    private City city;
    @Column
    private String sokak;
    @Column
    private String mahalle;
    @Column
    private String ilce;
    @Column
    private String binaNo;

    @ManyToOne
    private User user;

}

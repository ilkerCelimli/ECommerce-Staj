package com.portifolyo.mesleki1.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Adress extends BaseEntity {
    @OneToOne
    private City city;
    @Column
    private String adress;
    @ManyToOne
    private User user;




}

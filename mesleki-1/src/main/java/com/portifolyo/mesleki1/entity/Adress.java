package com.portifolyo.mesleki1.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Adress extends BaseEntity {
    private String title;
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

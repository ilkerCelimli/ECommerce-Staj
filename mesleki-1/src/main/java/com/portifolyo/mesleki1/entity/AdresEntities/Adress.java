package com.portifolyo.mesleki1.entity.AdresEntities;

import com.portifolyo.mesleki1.entity.BaseEntity;
import com.portifolyo.mesleki1.entity.UserAdress;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(callSuper = true)
public class Adress extends BaseEntity {

    @OneToOne
    @Column
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
    private UserAdress userAdress;

}

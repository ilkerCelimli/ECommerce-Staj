package com.portifolyo.mesleki1.entity.AdresEntities;

import com.portifolyo.mesleki1.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class City extends BaseEntity {

    private String code;
    private String city;
    @ManyToOne(cascade = CascadeType.ALL)
    private Country country;

}

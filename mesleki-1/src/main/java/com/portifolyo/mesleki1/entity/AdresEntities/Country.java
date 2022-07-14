package com.portifolyo.mesleki1.entity.AdresEntities;

import com.portifolyo.mesleki1.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Country extends BaseEntity {
    private String code;
    private String country;
     @OneToMany(mappedBy = "country")
    private List<City> cities;


}

package com.portifolyo.mesleki1.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Country extends BaseEntity {
    private String code;
    private String country;
   /*  @OneToMany(mappedBy = "country", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<City> cities;
*/

}

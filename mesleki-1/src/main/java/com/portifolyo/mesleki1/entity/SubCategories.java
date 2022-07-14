package com.portifolyo.mesleki1.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@Entity
public class SubCategories extends BaseEntity {

    private String name;
    private String description;
    @ManyToOne
    private Categories categories;

}

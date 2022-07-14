package com.portifolyo.mesleki1.entity;

import com.portifolyo.mesleki1.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)

public class Categories extends BaseEntity {

    private String name;
    private String description;
    @OneToMany(mappedBy = "categories")
    private List<Product> products;
}

package com.portifolyo.mesleki1.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@ToString
public class Categories extends BaseEntity {

    private String name;
    private String description;



    public Categories(String name , String description) {
        this.name = name;
        this.description = description;
    }
}

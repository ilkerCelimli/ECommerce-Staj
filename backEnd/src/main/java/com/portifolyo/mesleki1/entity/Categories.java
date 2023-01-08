package com.portifolyo.mesleki1.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Categories extends BaseEntity {

    private String name;
    private String description;
    private byte[] image;


    public Categories(String name , String description ,byte[] img) {
        this.name = name;
        this.description = description;
        this.image = img;
    }
}

package com.portifolyo.mesleki1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.portifolyo.mesleki1.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.List;

@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Categories extends BaseEntity {

    private String name;
    private String description;



    public Categories(String name , String description) {
        this.name = name;
        this.description = description;
    }
}

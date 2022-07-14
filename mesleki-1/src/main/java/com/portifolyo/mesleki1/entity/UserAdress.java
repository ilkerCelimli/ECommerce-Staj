package com.portifolyo.mesleki1.entity;

import com.portifolyo.mesleki1.entity.AdresEntities.Adress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class UserAdress extends BaseEntity {

    @OneToMany(mappedBy = "userAdress")
    private List<User> userList;
    @OneToMany(mappedBy = "userAdress")
    private List<Adress> adressList;

}

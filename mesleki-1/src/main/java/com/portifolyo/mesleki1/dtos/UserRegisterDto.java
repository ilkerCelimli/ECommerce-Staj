package com.portifolyo.mesleki1.dtos;

import com.portifolyo.mesleki1.enums.ROLE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto {

    private String name;
    private String surname;
    private ROLE role;
    private String phoneNumber;
    private String email;
    private String password;
    private Date birtday;
    private String tcNo;



}

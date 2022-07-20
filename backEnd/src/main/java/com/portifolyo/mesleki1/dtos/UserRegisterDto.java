package com.portifolyo.mesleki1.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto {

    @Size(min = 3, max = 20, message = "name must be between 3 and 20 characters")
    private String name;
    @Size(min = 3, max = 20, message = "surname must be between 3 and 20 characters")

    private String surname;
    private String role;
    private String phoneNumber;
    @Email(message = "Email is not valid")
    private String email;
    @Size(min = 6, max = 20, message = "password must be between 6 and 20 characters")
    private String password;
    private Date birtday;
    private long tcNo;
    private AdressDto adress;



}

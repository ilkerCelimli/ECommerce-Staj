package com.portifolyo.mesleki1.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserRegisterDto(

        @Size(min = 3, max = 20, message = "name must be between 3 and 20 characters")
        String name,
        @Size(min = 3, max = 20, message = "surname must be between 3 and 20 characters")
        String surname,
        String role,
        String phoneNumber,
        @Email(message = "Email is not valid")
        String email,
        @Size(min = 6, max = 20, message = "password must be between 6 and 20 characters")
        String password,
        Date birtday,
        long tcNo,
        AdressDto adress
) {


}

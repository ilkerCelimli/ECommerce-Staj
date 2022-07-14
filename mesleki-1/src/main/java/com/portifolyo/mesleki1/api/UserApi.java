package com.portifolyo.mesleki1.api;

import com.portifolyo.mesleki1.dtos.UserRegisterDto;
import com.portifolyo.mesleki1.exceptions.SqlExceptionCustom;
import com.portifolyo.mesleki1.exceptions.apiexception.EmailActiviteException;
import com.portifolyo.mesleki1.exceptions.apiexception.UserRegisterException;
import com.portifolyo.mesleki1.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserApi {

    private final UserServices userServices;

    @ExceptionHandler({UserRegisterException.class,SQLException.class})
    @PostMapping("/registerUser")
    public ResponseEntity registerUser(@RequestBody UserRegisterDto userRegisterDto) throws SQLException {
        userServices.userRegister(userRegisterDto);
        return ResponseEntity.created(null).build();
    }
    @GetMapping("/activitemail/{code}")
    @ExceptionHandler({EmailActiviteException.class,SQLException.class})
    public ResponseEntity ActiveMail(@PathVariable String code) throws SQLException {
        boolean result = userServices.activiteEmail(code);

        return result ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @ExceptionHandler({SqlExceptionCustom.class})
    @PostMapping("/updateUser/{id}")
    public ResponseEntity updateUser(@PathVariable String id , @RequestBody UserRegisterDto userRegisterDto) throws SQLException {
       boolean result = this.userServices.updateUser(id,userRegisterDto);
        return result ? ResponseEntity.ok().build() : null;
    }

    }





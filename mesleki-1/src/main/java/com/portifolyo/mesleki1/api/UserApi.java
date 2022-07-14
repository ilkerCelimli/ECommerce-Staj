package com.portifolyo.mesleki1.api;

import com.portifolyo.mesleki1.dtos.UserRegisterDto;
import com.portifolyo.mesleki1.exceptions.SqlExceptionCustom;
import com.portifolyo.mesleki1.exceptions.apiexception.EmailActiviteException;
import com.portifolyo.mesleki1.exceptions.apiexception.NotFoundException;
import com.portifolyo.mesleki1.exceptions.apiexception.UserRegisterException;
import com.portifolyo.mesleki1.services.UserServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;


@RestController
@RequestMapping("/api/user")
public class UserApi {

    private final UserServices userServices;

    public UserApi(UserServices userServicesImpl) {
        this.userServices = userServicesImpl;
    }


    @ExceptionHandler({UserRegisterException.class, SQLException.class})
    @PostMapping("/registerUser")
    public ResponseEntity registerUser(@RequestBody @Valid UserRegisterDto userRegisterDto) throws SQLException {
        userServices.userRegister(userRegisterDto);
        return ResponseEntity.created(null).build();
    }

    @GetMapping("/activitemail/{code}")
    @ExceptionHandler({EmailActiviteException.class, SQLException.class,NotFoundException.class})
    public ResponseEntity ActiveMail(@PathVariable String code) throws SQLException {
        boolean result = userServices.activiteEmail(code);

        return result ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @ExceptionHandler({SqlExceptionCustom.class, NotFoundException.class})
    @PostMapping("/updateUser/{id}")
    public ResponseEntity updateUser(@PathVariable String id, @RequestBody UserRegisterDto userRegisterDto) throws SQLException {
        boolean result = this.userServices.updateUser(id, userRegisterDto);
        return result ? ResponseEntity.ok().build() : null;
    }

    @GetMapping("/forgotPassword/{email}")
    public ResponseEntity forgotPassword(@PathVariable String email) {
        this.userServices.resetPasswordRequest(email);
        return ResponseEntity.ok().build();
    }

   @GetMapping("/resetPassword/{id}/{password}")
    public ResponseEntity resetPassword(@PathVariable("id") String id,@PathVariable("password") String password) {
        return ResponseEntity.ok().build();
   }



}





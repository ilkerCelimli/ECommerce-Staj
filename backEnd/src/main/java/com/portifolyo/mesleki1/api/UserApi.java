package com.portifolyo.mesleki1.api;

import com.portifolyo.mesleki1.dtos.LoginDto;
import com.portifolyo.mesleki1.dtos.UserRegisterDto;
import com.portifolyo.mesleki1.exceptions.apiexception.SqlExceptionCustom;
import com.portifolyo.mesleki1.services.UserServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.sql.SQLException;


@RestController
@RequestMapping("/api/user")

public class UserApi {

    private final UserServices userServices;
    public UserApi(UserServices userServicesImpl) {
        this.userServices = userServicesImpl;
    }


    @PostMapping("/login")
    public ResponseEntity<Object> login(LoginDto dto) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/sendMail")
    public ResponseEntity<Object> sendMail() throws MessagingException {
        this.userServices.sendUserEmail("ilker-7@hotmail.com","null0","null");
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/public/registerUser")
    public ResponseEntity<Object> registerUser(@RequestBody @Valid UserRegisterDto userRegisterDto) throws SQLException {
        userServices.userRegister(userRegisterDto);
        return ResponseEntity.created(null).build();
    }

    @GetMapping("/activitemail/{code}")
    public ResponseEntity<Object> ActiveMail(@PathVariable String code) throws SQLException {
        boolean result = userServices.activiteEmail(code);

        return result ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable String id, @RequestBody UserRegisterDto userRegisterDto) throws SQLException {
        boolean result = this.userServices.updateUser(id, userRegisterDto);
        return result ? ResponseEntity.ok().build() : null;
    }

    @GetMapping("/public/forgotPassword/{email}")
    public ResponseEntity<Object> forgotPassword(@PathVariable String email) {
        this.userServices.resetPasswordRequest(email);
        return ResponseEntity.ok().build();
    }

   @GetMapping("/public/resetPassword/{id}/{password}")
    public ResponseEntity<Object> resetPassword(@PathVariable("id") String id,@PathVariable("password") String password) throws SqlExceptionCustom {
       this.userServices.changePassword(id,password);
       return ResponseEntity.ok().build();
   }



}





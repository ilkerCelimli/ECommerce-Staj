package com.portifolyo.mesleki1.api;

import com.portifolyo.mesleki1.dtos.UserRegisterDto;
import com.portifolyo.mesleki1.exceptions.SqlExceptionCustom;
import com.portifolyo.mesleki1.services.UserServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.sql.SQLException;


@RestController
@RequestMapping("/api/user")
@CrossOrigin(allowedHeaders = "*",methods = {RequestMethod.OPTIONS,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})

public class UserApi {

    private final UserServices userServices;

    public UserApi(UserServices userServicesImpl) {
        this.userServices = userServicesImpl;
    }


    @GetMapping("/sendMail")
    public ResponseEntity<Object> sendMail() throws MessagingException {
        this.userServices.SendUserEmail("ilker-7@hotmail.com");
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/registerUser")
    public ResponseEntity<Object> registerUser(@RequestBody UserRegisterDto userRegisterDto) throws SQLException {
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

    @GetMapping("/forgotPassword/{email}")
    public ResponseEntity<Object> forgotPassword(@PathVariable String email) {
        this.userServices.resetPasswordRequest(email);
        return ResponseEntity.ok().build();
    }

   @GetMapping("/resetPassword/{id}/{password}")
    public ResponseEntity<Object> resetPassword(@PathVariable("id") String id,@PathVariable("password") String password) throws SqlExceptionCustom {
       this.userServices.ChangePassword(id,password);
       return ResponseEntity.ok().build();
   }



}




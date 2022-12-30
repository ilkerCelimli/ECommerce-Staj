package com.portifolyo.mesleki1.services;

import com.portifolyo.mesleki1.dtos.UserRegisterDto;
import com.portifolyo.mesleki1.entity.User;
import com.portifolyo.mesleki1.exceptions.apiexception.SqlExceptionCustom;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.mail.MessagingException;
import java.sql.SQLException;

public interface UserServices extends BaseServices<User>, UserDetailsService {

    boolean checkUserIsExists(String email);
    User findByEmail(String email);
    boolean checkUserIsActivated(String email);
   /* boolean checkUserEmailActivited(String email);
    boolean checkUserPassword(String email, String password); */
    boolean SendUserEmail(String email) throws MessagingException;
    void userRegister(UserRegisterDto dto) throws SQLException;
    boolean activiteEmail(String code) throws SQLException;
    void resetPasswordRequest(String email);
    boolean updateUser(String id,UserRegisterDto dto) throws SQLException;
    void ChangePassword(String id,String password) throws SqlExceptionCustom;


}

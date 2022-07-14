package com.portifolyo.mesleki1.services;

import com.portifolyo.mesleki1.dtos.UserRegisterDto;
import com.portifolyo.mesleki1.entity.User;
import com.portifolyo.mesleki1.exceptions.SqlExceptionCustom;

import java.sql.SQLException;

public interface UserServices extends BaseServices<User> {

    boolean checkUserIsExists(String email);
    boolean checkUserIsActivated(String email);
    boolean checkUserEmailActivited(String email);
    boolean checkUserPassword(String email, String password);
    boolean SendUserEmail(String email);
    boolean userRegister(UserRegisterDto dto) throws SQLException;
    boolean activiteEmail(String code) throws SQLException;
    void resetPasswordRequest(String email);
    boolean updateUser(String id,UserRegisterDto dto) throws SqlExceptionCustom;
    void ChangePassword(String id,String password) throws SqlExceptionCustom;


}

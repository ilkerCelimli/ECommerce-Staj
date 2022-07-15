package com.portifolyo.mesleki1.services.impl;

import com.portifolyo.mesleki1.dtos.UserRegisterDto;
import com.portifolyo.mesleki1.entity.Adress;
import com.portifolyo.mesleki1.entity.User;
import com.portifolyo.mesleki1.enums.ROLE;
import com.portifolyo.mesleki1.exceptions.SqlExceptionCustom;
import com.portifolyo.mesleki1.exceptions.apiexception.EmailActiviteException;
import com.portifolyo.mesleki1.exceptions.apiexception.NotFoundException;
import com.portifolyo.mesleki1.exceptions.apiexception.UserRegisterException;
import com.portifolyo.mesleki1.mappers.AdressDtoMapper;
import com.portifolyo.mesleki1.mappers.UserRegisterMapper;
import com.portifolyo.mesleki1.repository.UserRepository;
import com.portifolyo.mesleki1.services.AdressServices;
import com.portifolyo.mesleki1.services.ShopperService;
import com.portifolyo.mesleki1.services.UserServices;
import com.portifolyo.mesleki1.utils.RandomString;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class UserServicesImpl extends BaseServicesImpl<User> implements UserServices {


    private final UserRepository userRepository;
    private final UserRegisterMapper userRegisterMapper;
    private final AdressServices adressServices;
    private final AdressDtoMapper adressDtoMapper;
    private final ShopperService shopperService;

    public UserServicesImpl(UserRepository userRepository, UserRegisterMapper userRegisterMapper, AdressServices adressServices, AdressDtoMapper adressDtoMapper, ShopperService shopperService) {
        super(userRepository);
        this.userRepository = userRepository;
        this.userRegisterMapper = userRegisterMapper;
        this.adressServices = adressServices;
        this.adressDtoMapper = adressDtoMapper;
        this.shopperService = shopperService;
    }

    @Override
    public boolean checkUserIsExists(String email) {
        return this.userRepository.existsUserByEmail(email);
    }


    @Override
    public boolean checkUserIsActivated(String email) {
        if (checkUserIsExists(email)) {
            User u = this.userRepository.findUserByEmailEquals(email).get();
            return u.isActive() && u.isEmailActivated();

        } else throw new NotFoundException();
    }

    @Override
    public boolean checkUserEmailActivited(String email) {
        if (checkUserIsActivated(email)) {
            User u = this.userRepository.findUserByEmailEquals(email).get();
            return u.isEmailActivated();
        } else return false;
    }

    @Override
    public boolean checkUserPassword(String email, String password) {
        if (checkUserIsActivated(email)) {
            User u = this.userRepository.findUserByEmailEquals(email).get();
            return u.getPassword().equals(password);
        } else return false;
    }

    @Override
    public boolean SendUserEmail(String email) {
        return false;
    }

    @Override
    public boolean userRegister(UserRegisterDto dto) throws SQLException {
        if (!checkUserIsExists(dto.getEmail())) {
            User u = this.userRegisterMapper.toEntity(dto);
            u.setActive(true);
            u.setEmailActivated(false);
            u.setActivitionCode(new RandomString().nextString());
            User f = save(u);
            if (f.getRole().equals(ROLE.SHOP)) {
                boolean result = shopperService.shopperCheckandSave(f);
                if (!result) {
                    throw new UserRegisterException();
                }
            }
            Adress a = adressDtoMapper.toEntity(dto.getAdress());
            a.setUser(f);
            this.adressServices.save(a);
            return true;
        } else throw new UserRegisterException();
    }

    @Override
    public boolean activiteEmail(String code) throws SQLException {
        Optional<User> o = this.userRepository.findUserByActivitionCodeEquals(code);
        if (o.isPresent()) {
            User u = o.get();
            u.setEmailActivated(true);
            update(u);
            return true;
        } else throw new EmailActiviteException();
    }

    @Override //TODO Write This....
    public void resetPasswordRequest(String email) {
        if (checkUserIsExists(email)) {

        }
    }

    //TODO UpdateUser refactor
    @Override
    public boolean updateUser(String id, UserRegisterDto dto) throws SqlExceptionCustom {
        User u = findById(id);

        if (!Strings.isBlank(dto.getEmail()) || !Strings.isBlank(dto.getEmail()) || !Objects.isNull(dto.getEmail())) {
            dto.setEmail(u.getEmail());
        }

        if (!Strings.isBlank(dto.getPassword()) || !Strings.isEmpty(dto.getPassword()) || !Objects.isNull(dto.getPassword())) {
            dto.setPassword(u.getPassword());
        }

        if (!Strings.isBlank(dto.getName()) || !Strings.isEmpty(dto.getName()) || !Objects.isNull(dto.getName())) {
            dto.setName(u.getName());
        }

        if (!Strings.isBlank(dto.getSurname()) || !Strings.isEmpty(dto.getSurname()) || !Objects.isNull(dto.getSurname())) {
            dto.setSurname(u.getSurname());
        }
        //  u.setUpdatedAt(new Date());
        User f = update(u);

        log.info("Kullanıcı güncellendi {} {}", u.getId(), u.getUpdatedAt());
        return true;


    }

    @Override
    public void ChangePassword(String id, String password) throws SqlExceptionCustom {
        User u = findById(id);
        u.setPassword(password);
        save(u);
    }
}

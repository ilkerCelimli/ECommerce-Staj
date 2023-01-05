package com.portifolyo.mesleki1.services.impl;

import com.portifolyo.mesleki1.dtos.UserRegisterDto;
import com.portifolyo.mesleki1.entity.Adress;
import com.portifolyo.mesleki1.entity.User;
import com.portifolyo.mesleki1.enums.Role;
import com.portifolyo.mesleki1.exceptions.apiexception.SqlExceptionCustom;
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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.*;

@Service
@Slf4j
public class UserServicesImpl extends BaseServicesImpl<User> implements UserServices {


    private final UserRepository userRepository;
    private final UserRegisterMapper userRegisterMapper;
    private final AdressServices adressServices;
    private final AdressDtoMapper adressDtoMapper;
    private final ShopperService shopperService;
    private final JavaMailSender javaMailSender;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServicesImpl(UserRepository userRepository, UserRegisterMapper userRegisterMapper, AdressServices adressServices, AdressDtoMapper adressDtoMapper, ShopperService shopperService, JavaMailSender javaMailSender, BCryptPasswordEncoder bCryptPasswordEncoder) {
        super(userRepository);
        this.userRepository = userRepository;
        this.userRegisterMapper = userRegisterMapper;
        this.adressServices = adressServices;
        this.adressDtoMapper = adressDtoMapper;
        this.shopperService = shopperService;
        this.javaMailSender = javaMailSender;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public boolean checkUserIsExists(String email) {
        return this.userRepository.existsUserByEmail(email);
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> o = this.userRepository.findUserByEmailEquals(email);
      if(o.isPresent()) {
          return o.get();
      }
      else throw new NotFoundException();
    }


    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    public boolean checkUserIsActivated(String email) {
        if (checkUserIsExists(email)) {
            User u = this.userRepository.findUserByEmailEquals(email).get();
            return u.isActive() && u.isEmailActivated();

        } else throw new NotFoundException();
    }

    @Override
    public boolean sendUserEmail(String email) throws MessagingException {

        MimeMessage mime = javaMailSender.createMimeMessage();
        mime.setFrom(new InternetAddress("Meloonia52@gmail.com"));
        mime.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
        mime.setSubject("Test email");
        mime.setText("localhost:8080/api/user/activitemail/ZovxxqALp8ndYGUswwwfL");
        javaMailSender.send(mime);

        return true;
    }


    @Override
    @Transactional
    public void userRegister(UserRegisterDto dto) throws SQLException {
        if (!checkUserIsExists(dto.getEmail())) {
            User u = this.userRegisterMapper.toEntity(dto);
            u.setActive(true);
            u.setEmailActivated(false);
            u.setActivitionCode(new RandomString().nextString());
            u.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
            User f = save(u);
            if (f.getRole().equals(Role.SHOP)) {
                boolean result = shopperService.shopperCheckandSave(f);
                if (!result) throw new UserRegisterException();

            }
            Adress a = adressDtoMapper.toEntity(dto.getAdress());
            a.setUser(f);
            this.adressServices.save(a);
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

    }

    //TODO UpdateUser refactor
    @Override
    public boolean updateUser(String id, UserRegisterDto dto) throws SQLException {
        User u = findById(id);

        if (!Strings.isBlank(dto.getEmail()) || !Strings.isBlank(dto.getEmail()) || !Objects.isNull(dto.getEmail())) {
            u.setEmail(dto.getEmail());
        }

        if (!Strings.isBlank(dto.getPassword()) || !Strings.isEmpty(dto.getPassword()) || !Objects.isNull(dto.getPassword())) {
            u.setPassword(dto.getPassword());
        }

        if (!Strings.isBlank(dto.getName()) || !Strings.isEmpty(dto.getName()) || !Objects.isNull(dto.getName())) {
            u.setName(dto.getName());
        }

        if (!Strings.isBlank(dto.getSurname()) || !Strings.isEmpty(dto.getSurname()) || !Objects.isNull(dto.getSurname())) {
            u.setSurname(dto.getSurname());
        }

        if (Objects.nonNull(dto.getBirtday())) {
            u.setBirtday(dto.getBirtday());
        }
      /*  if ((Objects.nonNull(dto.getAdress()){

            Adress a = adressServices.findAdressByTitleAndUserId( u.getId());
            AdressDto s = dto.getAdress();

            if (Objects.nonNull(s.getAdress()) || !Strings.isBlank(s.getAdress()) || !Strings.isEmpty(s.getAdress())) {
                a.setAdress(s.getAdress());
            }
            this.adressServices.update(a);
        }*/
        update(u);

        log.info("Kullanıcı güncellendi {} {}", u.getId(), u.getUpdatedAt());
        return true;
    }

    @Override
    public void changePassword(String id, String password) throws SqlExceptionCustom {
        User u = findById(id);
        u.setPassword(password);
        save(u);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> u = this.userRepository.findUserByEmailEquals(username);
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority("User"));
        return u.isPresent() ? new org.springframework.security.core.userdetails.User(u.get().getName(),"",simpleGrantedAuthorities) : null;
    }
}

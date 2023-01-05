package com.portifolyo.mesleki1.entity;

import com.portifolyo.mesleki1.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "users")
@Table(name = "users")
public class User extends BaseEntity {


  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  private String surname;
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Role role;
  @Column(nullable = false)
  private String PhoneNumber;
  @Column(nullable = false)
  private String email;
  @Column(nullable = false)
  private String password;
  @Column
  private boolean EmailActivated = false;
  @Column(nullable = false)
  private Date birtday;
  @Column
  private long TcNo;
  @Column
  private String activitionCode;


  public User(String name, String surname, Role role, String phoneNumber, String email, String password, Date birtday, long tcNo, String activitionCode) {
    this.name = name;
    this.surname = surname;
    this.role = role;
    PhoneNumber = phoneNumber;
    this.email = email;
    this.password = password;
    this.birtday = birtday;
    TcNo = tcNo;
    this.activitionCode = activitionCode;
  }

}

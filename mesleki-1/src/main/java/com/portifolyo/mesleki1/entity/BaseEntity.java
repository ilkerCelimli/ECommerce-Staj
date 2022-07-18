package com.portifolyo.mesleki1.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @GenericGenerator(strategy = "uuid2", name = "uuid2")
    @GeneratedValue(generator = "uuid2")
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column
    private Date updatedAt;

    @Column
    private boolean isActive;
    @Column
    private boolean isDeleted;


}

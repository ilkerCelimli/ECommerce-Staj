package com.portifolyo.mesleki1.mappers;

import com.portifolyo.mesleki1.dtos.UserRegisterDto;
import com.portifolyo.mesleki1.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring",

        unmappedSourcePolicy = ReportingPolicy.ERROR,
        unmappedTargetPolicy = ReportingPolicy.ERROR)

public interface UserRegisterMapper {

    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "surname", target = "surname"),
            @Mapping(source = "role" ,target = "role"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "birtday", target = "birtday"),
            @Mapping(source = "TcNo", target = "TcNo"),

    })
    User toEntity(UserRegisterDto userRegisterDto);

}

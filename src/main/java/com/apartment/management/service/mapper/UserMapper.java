package com.apartment.management.service.mapper;

import com.apartment.management.entity.User;
import com.apartment.management.service.dto.RegisterUserDTO;
import com.apartment.management.service.dto.UserBasicDetailsDTO;
import com.apartment.management.service.dto.UserDTO;
import com.apartment.management.service.dto.UserRequestDTO;
import com.apartment.management.service.mapper.config.BaseMapperConfig;
import com.apartment.management.service.mapper.config.GenderMapper;
import com.apartment.management.service.mapper.config.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * A UserMapper.
 */
@Mapper(config = BaseMapperConfig.class, uses = {AuthorityRoleMapper.class, GenderMapper.class})
public interface UserMapper extends GenericMapper<User, UserDTO> {

    @Override
    @Mapping(target = "username", source = "email")
    User toEntity(UserDTO dto);

    @Override
    UserDTO toDto(User entity);

    @Mapping(target = "username", source = "email")
    @Mapping(target = "password", ignore = true)
    User toEntity(RegisterUserDTO dto);

    @Mapping(target = "username", source = "email")
    User toEntity(UserRequestDTO userRequestDTO);

    UserBasicDetailsDTO toBasicDetailsDto(User entity);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "phoneNumber", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "roles", ignore = true)
    void updateEntityFromDto(UserDTO dto, @MappingTarget User entity);
}

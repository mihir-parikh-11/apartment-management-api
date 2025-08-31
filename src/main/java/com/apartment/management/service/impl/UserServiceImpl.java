package com.apartment.management.service.impl;

import com.apartment.management.entity.User;
import com.apartment.management.exception.GlobalException;
import com.apartment.management.repository.UserRepository;
import com.apartment.management.service.AuthorityRoleService;
import com.apartment.management.service.UserService;
import com.apartment.management.service.dto.RegisterUserDTO;
import com.apartment.management.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A UserServiceImpl.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final AuthorityRoleService authorityRoleService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User findByUsername(String username) {
        log.info("User find by username : {} ", username);
        return userRepository.findByUsername(username).orElseThrow(() -> new GlobalException("User not found by username"));
    }

    @Override
    public void registerUser(RegisterUserDTO registerUserDTO) {
        log.info("Request for register user");
        User user = userMapper.toEntity(registerUserDTO);
        user.setRoles(List.of(authorityRoleService.findByRole("ROLE_USER")));
        user.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
        user.setStatus(Boolean.TRUE);
        userRepository.save(user);
    }
}

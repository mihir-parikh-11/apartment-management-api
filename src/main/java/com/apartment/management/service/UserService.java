package com.apartment.management.service;

import com.apartment.management.entity.User;
import com.apartment.management.service.dto.RegisterUserDTO;
import jakarta.validation.Valid;

/**
 * A UserService.
 */
public interface UserService {

    /**
     * Find By Username
     *
     * @param username a username
     * @return user Object
     */
    User findByUsername(String username);

    /**
     * Register User
     *
     * @param registerUserDTO a RegisterUserDTO
     */
    void registerUser(@Valid RegisterUserDTO registerUserDTO);
}

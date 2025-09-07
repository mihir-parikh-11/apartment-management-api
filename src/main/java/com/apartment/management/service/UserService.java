package com.apartment.management.service;

import com.apartment.management.entity.User;
import com.apartment.management.service.dto.*;
import jakarta.validation.Valid;

import java.util.List;

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

    /**
     * Get User by id
     *
     * @param id a id
     * @return UserDTO
     */
    UserDTO getUserById(Long id);

    /**
     * Update user Details
     *
     * @param userDTO a UserDTO
     * @return Updated UserDTO
     */
    UserDTO updateUser(UserDTO userDTO);

    /**
     * Get Current Login User Profile
     *
     * @return a UserDTO
     */
    UserDTO getCurrentLoginUserProfile();

    /**
     * Update User Roles
     *
     * @param userRoleDTO the userRoleDTO
     */
    void updateUserRoles(UserRoleDTO userRoleDTO);

    /**
     * Get all UserDTO
     *
     * @return list of UserDTO
     */
    List<UserDTO> getAllUsers();

    /**
     * Update user status
     *
     * @param userStatusDTO a UserStatusDTO
     */
    void updateUserStatus(UserStatusDTO userStatusDTO);

    /**
     * Add New User
     *
     * @param userRequestDTO a UserRequestDTO
     */
    void addUser(UserRequestDTO userRequestDTO);

    /**
     * Change Password
     *
     * @param passwordRequestDTO a passwordRequestDTO
     */
    void changePassword(PasswordRequestDTO passwordRequestDTO);

    /**
     * Find By id
     *
     * @param id userId
     * @return User entity
     */
    User findById(Long id);

    /**
     * Get All Managers
     *
     * @return a list of UserBasicDetailsDTO
     */
    List<UserBasicDetailsDTO> getAllManagers();

    /**
     * Get Owner By Email
     *
     * @param email ownerEmail
     * @return DTO
     */
    FlatOwnerDTO getOwnerByEmail(String email);

    /**
     * Add New Flat Owner
     *
     * @param owner DTO
     * @return Entity
     */
    User addFlatOwner(FlatOwnerDTO owner);
}

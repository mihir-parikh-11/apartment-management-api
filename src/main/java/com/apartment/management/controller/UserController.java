package com.apartment.management.controller;

import com.apartment.management.service.UserService;
import com.apartment.management.service.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * A UserController.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Slf4j
public class UserController {
    private final UserService userService;

    /**
     * A Register User
     *
     * @param registerUserDTO a RegisterUserDTO
     * @return a Void
     */
    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody RegisterUserDTO registerUserDTO) {
        log.info("REST request for register User");
        userService.registerUser(registerUserDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * A Get User by id
     *
     * @param id userId
     * @return a UserDTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        log.info("REST request to get user by id : {}", id);
        return ResponseEntity.ok(userService.getUserById(id));
    }

    /**
     * Update User Details
     *
     * @param userDTO UserDTO
     * @return updated UserDTO
     */
    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO) {
        log.info("REST request to update user details");
        return ResponseEntity.ok(userService.updateUser(userDTO));
    }

    /**
     * Get Current Login User Profile
     *
     * @return a UserDTO
     */
    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getCurrentLoginUserProfile() {
        log.info("REST request to get current login user profile");
        return ResponseEntity.ok(userService.getCurrentLoginUserProfile());
    }

    /**
     * Update User Roles
     *
     * @param userRoleDTO the userRoleDTO
     * @return Void
     */
    @PutMapping("/role-update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> updateUserRoles(@RequestBody UserRoleDTO userRoleDTO) {
        log.info("REST request to update User Roles : {} ", userRoleDTO);
        userService.updateUserRoles(userRoleDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * Get all User
     *
     * @return list of UserDTO
     */
    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        log.info("REST request to get all user");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * Update user status
     *
     * @param userStatusDTO a UserStatusDTO
     * @return Void
     */
    @PutMapping("/status")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> updateUserStatus(@RequestBody UserStatusDTO userStatusDTO) {
        log.info("REST request to update user status : {} ", userStatusDTO);
        userService.updateUserStatus(userStatusDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * Add User
     *
     * @param userRequestDTO a userRequestDTO
     * @return a Void
     */
    @PostMapping("/add")
    @PreAuthorize("!hasRole('ROLE_USER')")
    public ResponseEntity<Void> addUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        log.info("REST request for Add User");
        userService.addUser(userRequestDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * Change Password
     *
     * @param passwordRequestDTO a PasswordRequestDTO
     * @return a Void
     */
    @PutMapping("/change-password")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> changePassword(@Valid @RequestBody PasswordRequestDTO passwordRequestDTO) {
        log.info("REST request to change password");
        userService.changePassword(passwordRequestDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * Get All Managers
     *
     * @return List of UserBasicDetailsDTO
     */
    @GetMapping("/managers")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserBasicDetailsDTO>> getAllManagers() {
        log.info("REST request to get all Managers");
        return ResponseEntity.ok(userService.getAllManagers());
    }

    /**
     * Get Owner By Email
     *
     * @param email ownerEmail
     * @return DTO
     */
    @GetMapping("/owner")
    public ResponseEntity<FlatOwnerDTO> getOwnerByEmail(@RequestParam(name = "email") String email) {
        log.info("REST request to get Owner by email : {}", email);
        return ResponseEntity.ok(userService.getOwnerByEmail(email));
    }
}

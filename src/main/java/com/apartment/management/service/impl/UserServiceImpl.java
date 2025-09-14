package com.apartment.management.service.impl;

import com.apartment.management.entity.AuthorityRole;
import com.apartment.management.entity.User;
import com.apartment.management.exception.GlobalException;
import com.apartment.management.repository.UserRepository;
import com.apartment.management.service.AuthorityRoleService;
import com.apartment.management.service.UserService;
import com.apartment.management.service.dto.*;
import com.apartment.management.service.mapper.UserMapper;
import com.apartment.management.service.util.UserUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        validateNewUserEmailAndPhoneNumber(registerUserDTO.getEmail(), registerUserDTO.getPhoneNumber());
        User user = userMapper.toEntity(registerUserDTO);
        user.setRoles(List.of(authorityRoleService.findByRole("ROLE_USER")));
        user.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
        user.setStatus(Boolean.TRUE);
        userRepository.save(user);
    }

    @Override
    public UserDTO getUserById(Long id) {
        log.info("Request to get user by id : {}", id);
        return userMapper.toDto(findById(id));
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        log.info("Request to update user details");
        User user = userRepository.findById(userDTO.getId()).orElseThrow(() -> new GlobalException("User not found by id"));
        userMapper.updateEntityFromDto(userDTO, user);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDTO getCurrentLoginUserProfile() {
        log.info("Request to get current login user profile");
        return userMapper.toDto(UserUtility.getCurrentLoginUser());
    }

    @Override
    public void updateUserRoles(UserRoleDTO userRoleDTO) {
        log.info("Request to update User Roles for user with ID: {} ", userRoleDTO.getId());
        User user = userRepository.findById(userRoleDTO.getId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userRoleDTO.getId()));
        List<AuthorityRole> newRoles = userRoleDTO.getRoles()
                .stream()
                .map(roleDTO -> authorityRoleService.findById(roleDTO.getId()))
                .toList();
        user.getRoles().clear();
        user.setRoles(newRoles);
        userRepository.save(user);
        log.info("Successfully updated roles for user: {}", user.getUsername());
    }

    @Override
    public List<UserDTO> getAllUsers() {
        log.info("Request to get all user");
        return userMapper.toDto(userRepository.findAll());
    }

    @Override
    @Transactional
    public void updateUserStatus(UserStatusDTO userStatusDTO) {
        log.info("Request to update user status : {} ", userStatusDTO);
        User user = userRepository.findById(userStatusDTO.getId()).orElseThrow(() -> new GlobalException("User not found by id"));
        user.setStatus(userStatusDTO.getStatus());
    }

    @Override
    public void addUser(UserRequestDTO userRequestDTO) {
        log.info("REST request for Add User");
        validateNewUserEmailAndPhoneNumber(userRequestDTO.getEmail(), userRequestDTO.getPhoneNumber());
        User user = userMapper.toEntity(userRequestDTO);
        user.setStatus(Boolean.TRUE);
        userRepository.save(user);
    }

    @Override
    public void changePassword(PasswordRequestDTO passwordRequestDTO) {
        log.info("Request to change password");
        User user = userRepository.findById(passwordRequestDTO.getId()).orElseThrow(() -> new GlobalException("User not found by id"));
        user.setPassword(passwordEncoder.encode(passwordRequestDTO.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        log.info("Request to find user by id : {}", id);
        return userRepository.findById(id).orElseThrow(() -> new GlobalException("User not found by id"));
    }

    @Override
    public List<UserBasicDetailsDTO> getAllManagers() {
        log.info("Request to get all Managers");
        return userRepository.findAllByRolesRoleIn(List.of("ROLE_ADMIN", "ROLE_MANAGER")).stream().map(userMapper::toBasicDetailsDto).toList();
    }

    @Override
    public FlatOwnerDTO getOwnerByEmail(String email) {
        log.info("Request to get Owner by email : {}", email);
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return userMapper.toFlatOwnerDTO(user.get());
        } else {
            return FlatOwnerDTO.builder().email(email).build();
        }
    }

    @Override
    public User addFlatOwner(FlatOwnerDTO owner) {
        log.info("Request to save flat owner : {} ", owner);
        Optional<User> user = userRepository.findByEmail(owner.getEmail());
        if (user.isPresent()) {
            return user.get();
        } else {
            User entity = userMapper.toEntity(owner);
            entity.setRoles(List.of(authorityRoleService.findByRole("ROLE_USER")));
            entity.setStatus(Boolean.TRUE);
            return userRepository.save(entity);
        }
    }

    /**
     * Validate User by Email and Phone number
     *
     * @param email       email
     * @param phoneNumber phoneNumber
     */
    private void validateNewUserEmailAndPhoneNumber(String email, Long phoneNumber) {
        if (userRepository.existsByEmail(email)) {
            throw new GlobalException("User exist by email please use different Email");
        }
        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new GlobalException("User exist by Phone number please use different Phone number");
        }
    }
}

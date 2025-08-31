package com.apartment.management.controller;

import com.apartment.management.service.UserService;
import com.apartment.management.service.dto.RegisterUserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

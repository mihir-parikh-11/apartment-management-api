package com.apartment.management.controller;

import com.apartment.management.entity.User;
import com.apartment.management.exception.GlobalException;
import com.apartment.management.security.service.CustomUserDetails;
import com.apartment.management.security.service.JwtService;
import com.apartment.management.service.UserService;
import com.apartment.management.service.dto.AuthResponseDTO;
import com.apartment.management.service.dto.LoginDTO;
import com.apartment.management.service.dto.RefreshTokenRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A JwtAuthController.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class JwtAuthController {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JwtService jwtService;

    /**
     * Authenticate User API
     *
     * @param loginDTO a LoginDTO
     * @return a AuthResponseDTO
     */
    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponseDTO> authenticateUser(@Valid @RequestBody LoginDTO loginDTO) {
        log.info("REST request for login : {}", loginDTO);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (authenticate.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            User user = userService.findByUsername(loginDTO.getUsername());
            return ResponseEntity.ok(AuthResponseDTO.builder()
                    .accessToken(jwtService.generateAccessToken(user))
                    .refreshToken(jwtService.generateRefreshToken(user))
                    .build());
        } else {
            throw new GlobalException("Invalid Credentials");
        }
    }

    /**
     * A Refresh-Token API
     *
     * @param refreshTokenRequestDTO a RefreshTokenRequestDTO
     * @return a AuthResponseDTO
     */
    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponseDTO> refreshToken(@Valid @RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO) {
        log.info("REST request for Refresh Token");
        String username = jwtService.extractUsername(refreshTokenRequestDTO.getRefreshToken());
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new GlobalException("Something went wrong");
        }
        if (jwtService.validateToken(refreshTokenRequestDTO.getRefreshToken(), new CustomUserDetails(user))) {
            return ResponseEntity.ok(AuthResponseDTO.builder()
                    .accessToken(jwtService.generateAccessToken(user))
                    .refreshToken(refreshTokenRequestDTO.getRefreshToken())
                    .build());
        } else {
            throw new GlobalException("Session expired");
        }
    }
}

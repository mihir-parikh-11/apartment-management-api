package com.apartment.management.controller;

import com.apartment.management.service.AuthorityRoleService;
import com.apartment.management.service.dto.AuthorityRoleDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * A AuthorityRoleController
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/role")
@Slf4j
public class AuthorityRoleController {
    private final AuthorityRoleService authorityRoleService;

    /**
     * Get All Role
     *
     * @return list of AuthorityRoleDTO
     */
    @GetMapping("/all")
    public ResponseEntity<List<AuthorityRoleDTO>> getAllRole() {
        log.info("REST request for get all Role");
        return ResponseEntity.ok(authorityRoleService.getAllRole());
    }
}

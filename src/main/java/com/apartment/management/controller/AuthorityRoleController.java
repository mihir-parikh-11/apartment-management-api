package com.apartment.management.controller;

import com.apartment.management.service.AuthorityRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A AuthorityRoleController
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/role")
@Slf4j
public class AuthorityRoleController {
    private final AuthorityRoleService authorityRoleService;
}

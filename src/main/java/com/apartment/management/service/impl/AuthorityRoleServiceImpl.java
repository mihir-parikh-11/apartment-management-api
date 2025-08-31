package com.apartment.management.service.impl;

import com.apartment.management.entity.AuthorityRole;
import com.apartment.management.exception.GlobalException;
import com.apartment.management.repository.AuthorityRoleRepository;
import com.apartment.management.service.AuthorityRoleService;
import com.apartment.management.service.dto.AuthorityRoleDTO;
import com.apartment.management.service.mapper.AuthorityRoleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A AuthorityRoleServiceImpl.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorityRoleServiceImpl implements AuthorityRoleService {
    private final AuthorityRoleRepository authorityRoleRepository;

    private final AuthorityRoleMapper authorityRoleMapper;

    @Override
    public AuthorityRole findByRole(String role) {
        log.info("Request for Get Role by role : {}", role);
        return authorityRoleRepository.findByRole(role).orElseThrow(() -> new GlobalException("Role not found"));
    }

    @Override
    public List<AuthorityRoleDTO> getAllRole() {
        log.info("Request for get all Role");
        return authorityRoleRepository.findAll()
                .stream()
                .map(authorityRoleMapper::toDto)
                .toList();
    }
}

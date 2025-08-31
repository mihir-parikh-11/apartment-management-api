package com.apartment.management.service.impl;

import com.apartment.management.repository.AuthorityRoleRepository;
import com.apartment.management.service.AuthorityRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * A AuthorityRoleServiceImpl.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorityRoleServiceImpl implements AuthorityRoleService {
    private final AuthorityRoleRepository authorityRoleRepository;
}

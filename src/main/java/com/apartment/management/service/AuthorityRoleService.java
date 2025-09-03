package com.apartment.management.service;

import com.apartment.management.entity.AuthorityRole;
import com.apartment.management.service.dto.AuthorityRoleDTO;

import java.util.List;

/**
 * A AuthorityRoleService.
 */
public interface AuthorityRoleService {

    /**
     * Find AuthorityRole By Role
     *
     * @param role a role
     * @return a AuthorityRole Object
     */
    AuthorityRole findByRole(String role);

    /**
     * Get All Role
     *
     * @return AuthorityRoleDTO
     */
    List<AuthorityRoleDTO> getAllRole();

    /**
     * Get Role By ID
     *
     * @param id roleId
     * @return AuthorityRole
     */
    AuthorityRole findById(Long id);
}

package com.apartment.management.repository;

import com.apartment.management.entity.AuthorityRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * A AuthorityRoleRepository.
 */
@Repository
public interface AuthorityRoleRepository extends JpaRepository<AuthorityRole, Long> {

    /**
     * Find AuthorityRole By Role
     *
     * @param role a role
     * @return Optional AuthorityRole Object
     */
    Optional<AuthorityRole> findByRole(String role);
}

package com.apartment.management.repository;

import com.apartment.management.entity.AuthorityRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A AuthorityRoleRepository.
 */
@Repository
public interface AuthorityRoleRepository extends JpaRepository<AuthorityRole, Long> {
}

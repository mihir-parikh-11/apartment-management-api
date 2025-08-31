package com.apartment.management.repository;

import com.apartment.management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A UserRepository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

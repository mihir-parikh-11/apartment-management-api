package com.apartment.management.repository;

import com.apartment.management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * A UserRepository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find By Username
     *
     * @param username a username
     * @return Optional User Object
     */
    Optional<User> findByUsername(String username);

    List<User> findAllByRolesRoleIn(List<String> roles);
}

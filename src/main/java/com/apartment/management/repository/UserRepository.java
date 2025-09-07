package com.apartment.management.repository;

import com.apartment.management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
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

    /**
     * Find all by roles
     *
     * @param roles roles name
     * @return list of entity
     */
    List<User> findAllByRolesRoleIn(List<String> roles);

    /**
     * Find By Email
     *
     * @param email email
     * @return Optional User
     */
    Optional<User> findByEmail(String email);
}

package com.apartment.management.repository;

import com.apartment.management.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A GenderRepository.
 */
@Repository
public interface GenderRepository extends JpaRepository<Gender, Long> {
}

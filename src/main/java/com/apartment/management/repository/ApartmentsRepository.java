package com.apartment.management.repository;

import com.apartment.management.entity.Apartments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A ApartmentsRepository
 */
@Repository
public interface ApartmentsRepository extends JpaRepository<Apartments, Long> {
    List<Apartments> findAllByManagerId(Long managerId);
}

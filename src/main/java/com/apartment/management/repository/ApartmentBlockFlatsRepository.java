package com.apartment.management.repository;

import com.apartment.management.entity.ApartmentBlockFlats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A ApartmentBlockFlatsRepository
 */
@Repository
public interface ApartmentBlockFlatsRepository extends JpaRepository<ApartmentBlockFlats, Long> {
}

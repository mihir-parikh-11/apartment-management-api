package com.apartment.management.repository;

import com.apartment.management.entity.Apartments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A ApartmentsRepository
 */
@Repository
public interface ApartmentsRepository extends JpaRepository<Apartments, Long> {

    /**
     * Find All Apartments By ManagerId
     *
     * @param managerId managerId
     * @return list of Entity
     */
    List<Apartments> findAllByManagerId(Long managerId);

    /**
     * Find All Distinct Apartments By OwnerId
     *
     * @param ownerId ownerId
     * @return list of Entity
     */
    @Query("SELECT DISTINCT a FROM Apartments a " +
            "JOIN a.blocks ab " +
            "JOIN ab.flats abf " +
            "WHERE abf.owner.id = :ownerId")
    List<Apartments> findAllDistinctApartmentsByOwnerId(Long ownerId);
}

package com.apartment.management.repository;

import com.apartment.management.entity.ApartmentsBlocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A ApartmentsBlocksRepository
 */
@Repository
public interface ApartmentsBlocksRepository extends JpaRepository<ApartmentsBlocks, Long> {

    /**
     * Find All ApartmentsBlocks By ApartmentId
     *
     * @param apartmentId apartmentId
     * @return list of Entity
     */
    List<ApartmentsBlocks> findAllByApartmentsId(Long apartmentId);
}

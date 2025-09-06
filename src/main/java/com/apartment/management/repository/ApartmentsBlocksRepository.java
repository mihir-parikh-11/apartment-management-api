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
    List<ApartmentsBlocks> findAllByApartmentsId(Long apartmentId);
}

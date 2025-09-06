package com.apartment.management.service;

import com.apartment.management.entity.ApartmentsBlocks;
import com.apartment.management.service.dto.ApartmentsBlocksDTO;

import java.util.List;

/**
 * A ApartmentsBlocksService
 */
public interface ApartmentsBlocksService {

    /**
     * Save ApartmentsBlocks
     *
     * @param apartmentsBlocksDTO DTO
     * @return DTO
     */
    ApartmentsBlocksDTO saveApartmentsBlocks(ApartmentsBlocksDTO apartmentsBlocksDTO);

    /**
     * Update ApartmentsBlocks
     *
     * @param apartmentsBlocksDTO DTO
     * @return DTO
     */
    ApartmentsBlocksDTO updateApartmentsBlocks(ApartmentsBlocksDTO apartmentsBlocksDTO);

    /**
     * Find By id
     *
     * @param id apartmentsBlocksId
     * @return Entity
     */
    ApartmentsBlocks findById(Long id);

    /**
     * Get By id
     *
     * @param id apartmentsBlocksId
     * @return DTO
     */
    ApartmentsBlocksDTO getApartmentsBlocksById(Long id);

    /**
     * Get All ApartmentsBlocks by apartmentId
     *
     * @param apartmentId apartmentId
     * @return list of DTO
     */
    List<ApartmentsBlocksDTO> getAllApartmentsBlocksByApartmentId(Long apartmentId);
}

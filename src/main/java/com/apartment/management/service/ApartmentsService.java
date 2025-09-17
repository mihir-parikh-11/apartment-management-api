package com.apartment.management.service;

import com.apartment.management.entity.Apartments;
import com.apartment.management.entity.FlatsMaintenances;
import com.apartment.management.entity.enums.ApartmentsStatus;
import com.apartment.management.service.dto.ApartmentsRequestDTO;
import com.apartment.management.service.dto.ApartmentsResponseDTO;
import com.apartment.management.service.dto.MiscellaneousTransactionDTO;

import java.util.List;

/**
 * A ApartmentsService
 */
public interface ApartmentsService {

    /**
     * Get Apartments By id
     *
     * @param id apartmentsId
     * @return Apartments
     */
    Apartments findById(Long id);

    /**
     * Save Apartments
     *
     * @param apartmentsRequestDTO a apartmentsRequestDTO
     * @return ApartmentsResponseDTO
     */
    ApartmentsResponseDTO saveApartments(ApartmentsRequestDTO apartmentsRequestDTO);

    /**
     * Update Apartments
     *
     * @param apartmentsRequestDTO a apartmentsRequestDTO
     * @return ApartmentsResponseDTO
     */
    ApartmentsResponseDTO updateApartments(ApartmentsRequestDTO apartmentsRequestDTO);

    /**
     * Update Apartments Status
     *
     * @param id     apartmentsId
     * @param status status
     */
    void updateApartmentsStatus(Long id, Boolean status);

    /**
     * Update Apartments Approval Status
     *
     * @param id     apartmentsId
     * @param status ApartmentsStatus
     */
    void updateApartmentsApprovalStatus(Long id, ApartmentsStatus status);

    /**
     * Get Apartments by id
     *
     * @param id apartmentsId
     * @return ApartmentsResponseDTO
     */
    ApartmentsResponseDTO getApartmentsById(Long id);

    /**
     * Get ALl Apartments
     *
     * @return list of ApartmentsResponseDTO
     */
    List<ApartmentsResponseDTO> getAllApartments();

    /**
     * Update Apartment Manager
     *
     * @param id             apartmentsId
     * @param managerId      managerId
     * @param isRemoveManger isRemoveManger
     */
    void updateApartmentsManager(Long id, Long managerId, boolean isRemoveManger);

    /**
     * Get all owner Apartments
     *
     * @return list of DTO
     */
    List<ApartmentsResponseDTO> getAllOwnerApartment();

    /**
     * Update Apartment Available and Due Amount by apartmentId
     *
     * @param flatsMaintenances entity
     * @return Entity
     */
    Apartments updateApartmentsAvailableAndDueAmountByFlatMaintenance(FlatsMaintenances flatsMaintenances);

    /**
     * Update Apartment Available Amount by apartmentId
     *
     * @param miscellaneousTransactionDTO DTO
     * @return Entity
     */
    Apartments updateApartmentsAvailableByMiscellaneous(MiscellaneousTransactionDTO miscellaneousTransactionDTO);
}

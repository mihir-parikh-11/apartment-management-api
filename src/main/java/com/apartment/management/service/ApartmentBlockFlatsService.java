package com.apartment.management.service;

import com.apartment.management.entity.ApartmentBlockFlats;
import com.apartment.management.entity.FlatsMaintenances;
import com.apartment.management.service.dto.ApartmentBlockFlatsDTO;
import com.apartment.management.service.dto.FlatOwnerDTO;

import java.util.List;

/**
 * A ApartmentBlockFlatsService
 */
public interface ApartmentBlockFlatsService {

    /**
     * Find By id
     *
     * @param id flatId
     * @return Entity
     */
    ApartmentBlockFlats findById(Long id);

    /**
     * Save ApartmentBlockFlats
     *
     * @param apartmentBlockFlatsDTO DTO
     * @return DTO
     */
    ApartmentBlockFlatsDTO save(ApartmentBlockFlatsDTO apartmentBlockFlatsDTO);

    /**
     * Update flat owner
     *
     * @param id            flatId
     * @param flatOwnerDTO  Owner DTO
     * @param isRemoveOwner flag
     */
    void updateFlatOwner(Long id, FlatOwnerDTO flatOwnerDTO, boolean isRemoveOwner);

    /**
     * Get flat by id
     *
     * @param id flatId
     * @return DTO
     */
    ApartmentBlockFlatsDTO getFlatById(Long id);

    /**
     * Update Flat Paid and Due Maintenance by flatId
     *
     * @param flatsMaintenances entity
     */
    void updateFlatPaidAndDueMaintenanceByFlatMaintenance(FlatsMaintenances flatsMaintenances);

    /**
     * Get All Entity
     *
     * @return list of Entity
     */
    List<ApartmentBlockFlats> findAll();
}

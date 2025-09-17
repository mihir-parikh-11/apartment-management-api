package com.apartment.management.service;

import com.apartment.management.entity.Apartments;
import com.apartment.management.entity.FlatsMaintenances;
import com.apartment.management.service.dto.MiscellaneousTransactionDTO;
import com.apartment.management.service.dto.TransactionsDTO;

import java.util.List;

/**
 * A TransactionsService
 */
public interface TransactionsService {

    /**
     * Save miscellaneous transactions
     *
     * @param miscellaneousTransactionDTO DTO
     */
    void saveMiscellaneousTransaction(MiscellaneousTransactionDTO miscellaneousTransactionDTO);

    /**
     * Get all Transactions for Apartment by id
     *
     * @param apartmentId apartmentId
     * @return list of DTO
     */
    List<TransactionsDTO> getAllTransactionsByApartmentId(Long apartmentId);

    /**
     * Get all Transactions for Flat by id
     *
     * @param flatId flatId
     * @return list of DTO
     */
    List<TransactionsDTO> getAllTransactionsForFlatId(Long flatId);

    /**
     * Add Flat Maintenance transaction
     *
     * @param flatsMaintenances entity
     * @param apartments        entity
     */
    void addFlatMaintenanceTransaction(FlatsMaintenances flatsMaintenances, Apartments apartments);
}

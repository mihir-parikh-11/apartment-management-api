package com.apartment.management.service;

import com.apartment.management.entity.FlatsMaintenances;
import com.apartment.management.entity.enums.MaintenanceStatus;
import com.apartment.management.entity.enums.PaymentMode;
import com.apartment.management.service.dto.FlatsMaintenancesRequestDTO;
import com.apartment.management.service.dto.FlatsMaintenancesResponseDTO;
import com.apartment.management.service.dto.MaintenanceStatusDTO;

import java.time.Month;
import java.time.Year;
import java.util.List;

/**
 * A FlatsMaintenancesService
 */
public interface FlatsMaintenancesService {

    /**
     * Find By id
     *
     * @param id FlatsMaintenancesId
     * @return entity
     */
    FlatsMaintenances findById(Long id);

    /**
     * Update Flat Maintenance Status
     *
     * @param flatsMaintenancesRequestDTO DTO
     */
    void updateFlatMaintenanceStatus(FlatsMaintenancesRequestDTO flatsMaintenancesRequestDTO);

    /**
     * Get Flat Maintenance by id
     *
     * @param id flatMaintenanceId
     * @return DTO
     */
    FlatsMaintenancesResponseDTO getById(Long id);

    /**
     * Get All Flat Maintenances by flatId, Status and Year
     *
     * @param flatId            flatId
     * @param maintenanceStatus maintenanceStatus
     * @param year              year
     * @return list of DTO
     */
    List<FlatsMaintenancesResponseDTO> getAllFlatMaintenancesByFlatIdAndStatusAndYear(Long flatId, MaintenanceStatus maintenanceStatus, Year year);

    /**
     * Get All Apartment Maintenances by flatId, Status, Month and Year
     *
     * @param apartmentId       apartmentId
     * @param maintenanceStatus maintenanceStatus
     * @param month             month
     * @param year              year
     * @return list of DTO
     */
    List<FlatsMaintenancesResponseDTO> getAllApartmentMaintenancesByApartmentIdAndStatusAndYear(Long apartmentId, MaintenanceStatus maintenanceStatus, Month month, Year year);

    /**
     * Scheduled task that runs at 12:05 AM UTC on the 1st day of every month
     * to create monthly flats maintenance records.
     */
    void createMonthlyFlatsMaintenances();

    /**
     * Get All Months
     *
     * @return list of Enums
     */
    List<Month> getAllMonths();

    /**
     * Get All PaymentMode
     *
     * @return list of Enums
     */
    List<PaymentMode> getAllPaymentMode();

    /**
     * Get All MaintenanceStatus
     *
     * @return list of Enums
     */
    List<MaintenanceStatusDTO> getAllMaintenanceStatus();

    /**
     * It checks for due flats maintenance and updates their due amounts.
     */
    void checkDueDateAndUpdateDueAmount();
}

package com.apartment.management.controller;

import com.apartment.management.entity.enums.MaintenanceStatus;
import com.apartment.management.entity.enums.PaymentMode;
import com.apartment.management.service.FlatsMaintenancesService;
import com.apartment.management.service.dto.FlatsMaintenancesRequestDTO;
import com.apartment.management.service.dto.FlatsMaintenancesResponseDTO;
import com.apartment.management.service.dto.MaintenanceStatusDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.time.Year;
import java.util.List;

/**
 * A FlatsMaintenancesController
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/maintenances")
@Slf4j
public class FlatsMaintenancesController {

    private final FlatsMaintenancesService flatsMaintenancesService;

    /**
     * Update Flat Maintenance Status
     *
     * @param flatsMaintenancesRequestDTO DTO
     * @return Void
     */
    @PutMapping
    public ResponseEntity<Void> updateFlatMaintenanceStatus(@RequestBody FlatsMaintenancesRequestDTO flatsMaintenancesRequestDTO) {
        log.info("REST request to update Flat Maintenance Status : {}", flatsMaintenancesRequestDTO);
        flatsMaintenancesService.updateFlatMaintenanceStatus(flatsMaintenancesRequestDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * Get Flat Maintenance by id
     *
     * @param id flatMaintenanceId
     * @return DTO
     */
    @GetMapping("/{flatMaintenanceId}")
    public ResponseEntity<FlatsMaintenancesResponseDTO> getById(@PathVariable(name = "flatMaintenanceId") Long id) {
        log.info("REST request to get Flat Maintenance by id : {}", id);
        return ResponseEntity.ok(flatsMaintenancesService.getById(id));
    }

    /**
     * Get All Flat Maintenances by flatId, Status and Year
     *
     * @param flatId            flatId
     * @param maintenanceStatus maintenanceStatus
     * @param year              year
     * @return list of DTO
     */
    @GetMapping("/flat/{flatId}")
    public ResponseEntity<List<FlatsMaintenancesResponseDTO>> getAllFlatMaintenancesByFlatIdAndStatusAndYear(@PathVariable(name = "flatId") Long flatId,
                                                                                                             @RequestParam(name = "status", required = false) MaintenanceStatus maintenanceStatus,
                                                                                                             @RequestParam(name = "year") Year year) {
        log.info("REST request to get all Flat maintenances by flatId : {}, status : {} and year : {}", flatId, maintenanceStatus, year);
        return ResponseEntity.ok(flatsMaintenancesService.getAllFlatMaintenancesByFlatIdAndStatusAndYear(flatId, maintenanceStatus, year));
    }

    /**
     * Get All Apartment Maintenances by flatId, Status, Month and Year
     *
     * @param apartmentId       apartmentId
     * @param maintenanceStatus maintenanceStatus
     * @param month             month
     * @param year              year
     * @return list of DTO
     */
    @GetMapping("/apartment/{apartmentId}")
    public ResponseEntity<List<FlatsMaintenancesResponseDTO>> getAllApartmentMaintenancesByApartmentIdAndStatusAndMonthAndYear(@PathVariable(name = "apartmentId") Long apartmentId,
                                                                                                                               @RequestParam(name = "status", required = false) MaintenanceStatus maintenanceStatus,
                                                                                                                               @RequestParam(name = "month", required = false) Month month,
                                                                                                                               @RequestParam(name = "year") Year year) {
        log.info("REST request to get All Apartment Maintenances by flatId : {}, Status : {}, Month : {} and Year : {}", apartmentId, maintenanceStatus, month, year);
        return ResponseEntity.ok(flatsMaintenancesService.getAllApartmentMaintenancesByApartmentIdAndStatusAndYear(apartmentId, maintenanceStatus, month, year));
    }

    /**
     * Get All Months
     *
     * @return list of Enums
     */
    @GetMapping("/months")
    public ResponseEntity<List<Month>> getAllMonths() {
        log.info("REST request to get all month");
        return ResponseEntity.ok(flatsMaintenancesService.getAllMonths());
    }

    /**
     * Get All PaymentMode
     *
     * @return list of Enums
     */
    @GetMapping("/paymentMode")
    public ResponseEntity<List<PaymentMode>> getAllPaymentMode() {
        log.info("REST request to get all PaymentMode");
        return ResponseEntity.ok(flatsMaintenancesService.getAllPaymentMode());
    }

    /**
     * Get All MaintenanceStatus
     *
     * @return list of Enums
     */
    @GetMapping("/status")
    public ResponseEntity<List<MaintenanceStatusDTO>> getAllMaintenanceStatus() {
        log.info("REST request to get all MaintenanceStatus");
        return ResponseEntity.ok(flatsMaintenancesService.getAllMaintenanceStatus());
    }
}

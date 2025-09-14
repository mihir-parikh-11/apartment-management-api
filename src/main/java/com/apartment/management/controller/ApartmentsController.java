package com.apartment.management.controller;

import com.apartment.management.entity.enums.ApartmentsStatus;
import com.apartment.management.service.ApartmentsService;
import com.apartment.management.service.dto.ApartmentsRequestDTO;
import com.apartment.management.service.dto.ApartmentsResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * A ApartmentsController
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/apartments")
@Slf4j
public class ApartmentsController {

    private final ApartmentsService apartmentsService;

    /**
     * Save Apartments
     *
     * @param apartmentsRequestDTO a apartmentsRequestDTO
     * @return ApartmentsResponseDTO
     */
    @PostMapping()
    @PreAuthorize("!hasRole('ROLE_USER')")
    public ResponseEntity<ApartmentsResponseDTO> saveApartments(@RequestBody ApartmentsRequestDTO apartmentsRequestDTO) {
        log.info("REST request to save apartments : {}", apartmentsRequestDTO);
        return ResponseEntity.ok(apartmentsService.saveApartments(apartmentsRequestDTO));
    }

    /**
     * Update Apartments
     *
     * @param apartmentsRequestDTO a apartmentsRequestDTO
     * @return ApartmentsResponseDTO
     */
    @PutMapping()
    @PreAuthorize("!hasRole('ROLE_USER')")
    public ResponseEntity<ApartmentsResponseDTO> updateApartments(@RequestBody ApartmentsRequestDTO apartmentsRequestDTO) {
        log.info("REST request to update apartments : {}", apartmentsRequestDTO);
        return ResponseEntity.ok(apartmentsService.updateApartments(apartmentsRequestDTO));
    }

    /**
     * Update Apartments Status
     *
     * @param id     apartmentsId
     * @param status status
     * @return Void
     */
    @PutMapping("/status/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> updateApartmentsStatus(@PathVariable(name = "id") Long id, @RequestParam(name = "status") Boolean status) {
        log.info("REST request to update Apartments : {} , Status : {}", id, status);
        apartmentsService.updateApartmentsStatus(id, status);
        return ResponseEntity.ok().build();
    }

    /**
     * Update Apartments Approval Status
     *
     * @param id     apartmentsId
     * @param status ApartmentsStatus
     * @return a Void
     */
    @PutMapping("/{id}/approval")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> updateApartmentsApprovalStatus(@PathVariable(name = "id") Long id, @RequestParam(name = "status") ApartmentsStatus status) {
        log.info("REST request to update Apartments : {} , Approval Status : {}", id, status);
        apartmentsService.updateApartmentsApprovalStatus(id, status);
        return ResponseEntity.ok().build();
    }

    /**
     * Get Apartments by id
     *
     * @param id apartmentsId
     * @return ApartmentsResponseDTO
     */
    @GetMapping
    public ResponseEntity<ApartmentsResponseDTO> getApartmentsById(@RequestParam(name = "id") Long id) {
        log.info("REST request to get Apartments by id : {}", id);
        return ResponseEntity.ok(apartmentsService.getApartmentsById(id));
    }

    /**
     * Get ALl Apartments
     *
     * @return list of ApartmentsResponseDTO
     */
    @GetMapping("/all")
    @PreAuthorize("!hasRole('ROLE_USER')")
    public ResponseEntity<List<ApartmentsResponseDTO>> getAllApartments() {
        log.info("REST request to get All Apartments");
        return ResponseEntity.ok(apartmentsService.getAllApartments());
    }

    /**
     * Update Apartment Manager
     *
     * @param id             apartmentsId
     * @param managerId      managerId
     * @param isRemoveManger isRemoveManger
     * @return Void
     */
    @PutMapping("/{id}/manager")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> updateApartmentsManager(@PathVariable(name = "id") Long id,
                                                        @RequestParam(name = "managerId", required = false) Long managerId,
                                                        @RequestParam(name = "isRemoveManger", required = false) boolean isRemoveManger) {
        log.info("REST request to update Apartment Manager by apartmentId : {} and managerId : {}", id, managerId);
        apartmentsService.updateApartmentsManager(id, managerId, isRemoveManger);
        return ResponseEntity.ok().build();
    }

    /**
     * Get all owner Apartments
     *
     * @return list of DTO
     */
    @GetMapping("/owner")
    public ResponseEntity<List<ApartmentsResponseDTO>> getAllOwnerApartment() {
        log.info("REST request to get all owner Apartments");
        return ResponseEntity.ok(apartmentsService.getAllOwnerApartment());
    }
}

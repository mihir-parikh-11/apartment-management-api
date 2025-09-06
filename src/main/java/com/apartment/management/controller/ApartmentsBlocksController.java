package com.apartment.management.controller;

import com.apartment.management.service.ApartmentsBlocksService;
import com.apartment.management.service.dto.ApartmentsBlocksDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * A ApartmentsBlocksController
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/blocks")
@Slf4j
public class ApartmentsBlocksController {

    private final ApartmentsBlocksService apartmentsBlocksService;

    /**
     * Save ApartmentsBlocks
     *
     * @param apartmentsBlocksDTO DTO
     * @return DTO
     */
    @PostMapping
    @PreAuthorize("!hasRole('ROLE_USER')")
    public ResponseEntity<ApartmentsBlocksDTO> saveApartmentsBlocks(@Valid @RequestBody ApartmentsBlocksDTO apartmentsBlocksDTO) {
        log.info("REST request to save ApartmentsBlocks : {} ", apartmentsBlocksDTO);
        return ResponseEntity.ok(apartmentsBlocksService.saveApartmentsBlocks(apartmentsBlocksDTO));
    }

    /**
     * Update ApartmentsBlocks
     *
     * @param apartmentsBlocksDTO DTO
     * @return DTO
     */
    @PutMapping
    @PreAuthorize("!hasRole('ROLE_USER')")
    public ResponseEntity<ApartmentsBlocksDTO> updateApartmentsBlocks(@Valid @RequestBody ApartmentsBlocksDTO apartmentsBlocksDTO) {
        log.info("REST request to update ApartmentsBlocks : {} ", apartmentsBlocksDTO);
        return ResponseEntity.ok(apartmentsBlocksService.updateApartmentsBlocks(apartmentsBlocksDTO));
    }

    /**
     * Get By id
     *
     * @param id apartmentsBlocksId
     * @return DTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApartmentsBlocksDTO> getApartmentsBlocksById(@PathVariable(name = "id") Long id) {
        log.info("REST request to get ApartmentsBlocks by id : {}", id);
        return ResponseEntity.ok(apartmentsBlocksService.getApartmentsBlocksById(id));
    }

    /**
     * Get All ApartmentsBlocks by apartmentId
     *
     * @param apartmentId apartmentId
     * @return list of DTO
     */
    @GetMapping("/apartment/{apartmentId}")
    public ResponseEntity<List<ApartmentsBlocksDTO>> getAllApartmentsBlocksByApartmentId(@PathVariable(name = "apartmentId") Long apartmentId) {
        log.info("REST request to get All ApartmentsBlocks by apartmentId : {}", apartmentId);
        return ResponseEntity.ok(apartmentsBlocksService.getAllApartmentsBlocksByApartmentId(apartmentId));
    }
}

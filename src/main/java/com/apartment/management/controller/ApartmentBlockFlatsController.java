package com.apartment.management.controller;

import com.apartment.management.service.ApartmentBlockFlatsService;
import com.apartment.management.service.dto.ApartmentBlockFlatsDTO;
import com.apartment.management.service.dto.FlatOwnerDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * A ApartmentBlockFlatsController
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/flats")
@Slf4j
public class ApartmentBlockFlatsController {

    private final ApartmentBlockFlatsService apartmentBlockFlatsService;

    /**
     * Save ApartmentBlockFlats
     *
     * @param apartmentBlockFlatsDTO DTO
     * @return DTO
     */
    @PostMapping
    public ResponseEntity<ApartmentBlockFlatsDTO> saveFlats(@Valid @RequestBody ApartmentBlockFlatsDTO apartmentBlockFlatsDTO) {
        log.info("REST request to save flat : {}", apartmentBlockFlatsDTO);
        return ResponseEntity.ok(apartmentBlockFlatsService.save(apartmentBlockFlatsDTO));
    }

    /**
     * Update flat owner
     *
     * @param id            flatId
     * @param flatOwnerDTO  Owner DTO
     * @param isRemoveOwner flag
     * @return Void
     */
    @PutMapping("/{flatId}/update-owner")
    public ResponseEntity<Void> updateFlatOwner(@PathVariable(name = "flatId") Long id,
                                                @RequestBody(required = false) FlatOwnerDTO flatOwnerDTO,
                                                @RequestParam(name = "isRemoveOwner", defaultValue = "false") boolean isRemoveOwner) {
        log.info("REST request to update flat owner. Flat id : {}, Owner : {} and isRemoveOwner : {}", id, flatOwnerDTO, isRemoveOwner);
        apartmentBlockFlatsService.updateFlatOwner(id, flatOwnerDTO, isRemoveOwner);
        return ResponseEntity.ok().build();
    }

    /**
     * Get flat by id
     *
     * @param id flatId
     * @return DTO
     */
    @GetMapping("/{flatId}")
    public ResponseEntity<ApartmentBlockFlatsDTO> getFlatById(@PathVariable(name = "flatId") Long id) {
        log.info("REST request to get flat by id : {}", id);
        return ResponseEntity.ok(apartmentBlockFlatsService.getFlatById(id));
    }
}

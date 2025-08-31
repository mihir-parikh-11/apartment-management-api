package com.apartment.management.controller;

import com.apartment.management.service.GenderService;
import com.apartment.management.service.dto.GenderDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * A GenderController.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gender")
@Slf4j
public class GenderController {
    private final GenderService genderService;

    /**
     * Get All Gender
     *
     * @return list of GenderDTO
     */
    @GetMapping("/all")
    public ResponseEntity<List<GenderDTO>> getAllGender() {
        log.info("REST request for get all Gender");
        return ResponseEntity.ok(genderService.getAllGender());
    }
}

package com.apartment.management.service;

import com.apartment.management.service.dto.GenderDTO;

import java.util.List;

/**
 * A GenderService.
 */
public interface GenderService {

    /**
     * Get All Gender
     *
     * @return a list of GenetDTO
     */
    List<GenderDTO> getAllGender();
}

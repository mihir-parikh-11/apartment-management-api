package com.apartment.management.service.impl;

import com.apartment.management.repository.GenderRepository;
import com.apartment.management.service.GenderService;
import com.apartment.management.service.dto.GenderDTO;
import com.apartment.management.service.mapper.GenderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A GenderServiceImpl.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class GenderServiceImpl implements GenderService {
    private final GenderRepository genderRepository;

    private final GenderMapper genderMapper;

    @Override
    public List<GenderDTO> getAllGender() {
        log.info("Request for get all Gender");
        return genderRepository.findAll().stream().map(genderMapper::toDto).toList();
    }
}

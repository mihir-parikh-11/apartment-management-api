package com.apartment.management.service.impl;

import com.apartment.management.repository.GenderRepository;
import com.apartment.management.service.GenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * A GenderServiceImpl.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class GenderServiceImpl implements GenderService {
    private final GenderRepository genderRepository;
}

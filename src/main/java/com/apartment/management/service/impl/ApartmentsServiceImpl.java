package com.apartment.management.service.impl;

import com.apartment.management.repository.ApartmentsRepository;
import com.apartment.management.service.ApartmentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * A ApartmentsServiceImpl
 */
@Service
@RequiredArgsConstructor
public class ApartmentsServiceImpl implements ApartmentsService {
    private final ApartmentsRepository apartmentsRepository;
}

package com.apartment.management.controller;

import com.apartment.management.service.ApartmentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A ApartmentsController
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/apartments")
public class ApartmentsController {
    private final ApartmentsService apartmentsService;
}

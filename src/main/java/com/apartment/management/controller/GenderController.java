package com.apartment.management.controller;

import com.apartment.management.service.GenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A GenderController.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gender")
@Slf4j
public class GenderController {
    private final GenderService genderService;
}

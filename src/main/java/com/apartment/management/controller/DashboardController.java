package com.apartment.management.controller;

import com.apartment.management.service.DashboardService;
import com.apartment.management.service.dto.DashboardDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A DashboardController.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dashboard")
@Slf4j
public class DashboardController {

    private final DashboardService dashboardService;

    /**
     * Get Dashboard Data
     *
     * @return DTO
     */
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DashboardDTO> getDashboardData() {
        log.info("REST request get dashboard data");
        return ResponseEntity.ok(dashboardService.getDashboardData());
    }
}

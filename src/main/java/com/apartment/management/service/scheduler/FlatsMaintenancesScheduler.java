package com.apartment.management.service.scheduler;

import com.apartment.management.service.FlatsMaintenancesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * A FlatsMaintenancesScheduler
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class FlatsMaintenancesScheduler {

    private final FlatsMaintenancesService flatsMaintenancesService;

    /**
     * Scheduled task that runs at 12:05 AM UTC on the 1st day of every month
     * to create monthly flats maintenance records.
     */
    @Scheduled(cron = "0 5 0 1 * *", zone = "UTC")
//    @Scheduled(cron = "0 */2 * * * *", zone = "UTC")
    public void createMonthlyFlatsMaintenances() {
        log.info("Scheduler triggered: Starting monthly flats maintenance creation at {}", LocalDateTime.now());
        flatsMaintenancesService.createMonthlyFlatsMaintenances();
    }
}

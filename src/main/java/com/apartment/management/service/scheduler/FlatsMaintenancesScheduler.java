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
//    @Scheduled(cron = "0 */2 * * * *", zone = "UTC")  // For Local Testing
    public void createMonthlyFlatsMaintenances() {
        log.info("Scheduler triggered: Starting monthly flats maintenance creation at {}", LocalDateTime.now());
        flatsMaintenancesService.createMonthlyFlatsMaintenances();
    }

    /**
     * This method is scheduled to run every day at 12:15 AM (midnight).
     * It checks for due flats maintenance and updates their due amounts.
     */
    @Scheduled(cron = "0 15 0 * * *", zone = "UTC")
//    @Scheduled(cron = "0 */2 * * * *", zone = "UTC") // For Local Testing
    public void checkDueDateAndUpdateDueAmount() {
        log.info("Scheduled task started: Checking due dates and updating due amounts...");
        flatsMaintenancesService.checkDueDateAndUpdateDueAmount();
    }
}

package com.apartment.management.service.impl;

import com.apartment.management.entity.ApartmentBlockFlats;
import com.apartment.management.entity.Apartments;
import com.apartment.management.entity.FlatsMaintenances;
import com.apartment.management.entity.enums.MaintenanceStatus;
import com.apartment.management.entity.enums.PaymentMode;
import com.apartment.management.exception.GlobalException;
import com.apartment.management.repository.FlatsMaintenancesRepository;
import com.apartment.management.service.ApartmentBlockFlatsService;
import com.apartment.management.service.ApartmentsService;
import com.apartment.management.service.FlatsMaintenancesService;
import com.apartment.management.service.dto.FlatsMaintenancesRequestDTO;
import com.apartment.management.service.dto.FlatsMaintenancesResponseDTO;
import com.apartment.management.service.dto.MaintenanceStatusDTO;
import com.apartment.management.service.mapper.FlatsMaintenancesMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Month;
import java.time.Year;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * A FlatsMaintenancesServiceImpl
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FlatsMaintenancesServiceImpl implements FlatsMaintenancesService {

    private final FlatsMaintenancesRepository flatsMaintenancesRepository;

    private final ApartmentsService apartmentsService;

    private final ApartmentBlockFlatsService apartmentBlockFlatsService;

    private final FlatsMaintenancesMapper flatsMaintenancesMapper;

    @Override
    public FlatsMaintenances findById(Long id) {
        log.info("Request to find FlatsMaintenances By id : {}", id);
        return flatsMaintenancesRepository.findById(id).orElseThrow(() -> new GlobalException("FlatsMaintenances not found"));
    }

    @Override
    @Transactional
    public void updateFlatMaintenanceStatus(FlatsMaintenancesRequestDTO flatsMaintenancesRequestDTO) {
        log.info("Request to update Flat Maintenance Status : {}", flatsMaintenancesRequestDTO);
        FlatsMaintenances flatsMaintenances = findById(flatsMaintenancesRequestDTO.getId());
        flatsMaintenances.setMaintenanceStatus(flatsMaintenancesRequestDTO.getMaintenanceStatus());
        flatsMaintenances.setPaymentMode(flatsMaintenancesRequestDTO.getPaymentMode());
        flatsMaintenances = flatsMaintenancesRepository.save(flatsMaintenances);
        apartmentBlockFlatsService.updateFlatPaidAndDueMaintenanceByFlatMaintenance(flatsMaintenances);
        apartmentsService.updateApartmentsAvailableAndDueAmountByFlatMaintenance(flatsMaintenances);
    }

    @Override
    public FlatsMaintenancesResponseDTO getById(Long id) {
        log.info("Request to get Flat Maintenance by id : {}", id);
        return flatsMaintenancesMapper.toDto(findById(id));
    }

    @Override
    public List<FlatsMaintenancesResponseDTO> getAllFlatMaintenancesByFlatIdAndStatusAndYear(Long flatId, MaintenanceStatus maintenanceStatus, Year year) {
        log.info("Request to get all maintenances by flatId : {}, status : {} and year : {}", flatId, maintenanceStatus, year);
        Integer yearInt = (year == null) ? Year.now().getValue() : year.getValue();
        return flatsMaintenancesRepository.findAllByFlatIdAndOptionalStatusAndYear(flatId, maintenanceStatus, yearInt).stream().map(flatsMaintenancesMapper::toDto).toList();
    }

    @Override
    public List<FlatsMaintenancesResponseDTO> getAllApartmentMaintenancesByApartmentIdAndStatusAndYear(Long apartmentId, MaintenanceStatus maintenanceStatus, Month month, Year year) {
        log.info("Request to get All Apartment Maintenances by flatId : {}, Status : {}, Month : {} and Year : {}", apartmentId, maintenanceStatus, month, year);
        Integer yearInt = (year == null) ? Year.now().getValue() : year.getValue();
        Integer monthInt = (month == null) ? null : month.getValue();
        return flatsMaintenancesRepository.findAllByApartmentIdAndOptionalStatusAndOptionalMonthAndYear(apartmentId, maintenanceStatus, monthInt, yearInt)
                .stream().map(flatsMaintenancesMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public void createMonthlyFlatsMaintenances() {
        log.info("Starting creation of monthly maintenance records...");
        List<ApartmentBlockFlats> apartmentBlockFlats = apartmentBlockFlatsService.findAll();
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        for (ApartmentBlockFlats flat : apartmentBlockFlats) {
            try {
                Apartments apartments = flat.getApartmentsBlocks().getApartments();
                FlatsMaintenances flatsMaintenances = FlatsMaintenances.builder()
                        .flat(flat)
                        .maintenanceAmount(apartments.getPerFlatMaintenance())
                        .maintenanceStatus(MaintenanceStatus.PENDING)
                        .dueDate(now.plusDays(apartments.getMaintenanceDueDays()))
                        .dueAmount(apartments.getChargesPerDayDue())
                        .maintenanceMonth(now.getMonth().getValue())
                        .maintenanceYear(Year.now().getValue())
                        .totalMaintenanceAmount(apartments.getPerFlatMaintenance())
                        .build();
                flatsMaintenances = flatsMaintenancesRepository.save(flatsMaintenances);
                apartments.setDueAmount(apartments.getDueAmount() + flatsMaintenances.getTotalMaintenanceAmount());
                flat.setDueMaintenance(flat.getDueMaintenance() + flatsMaintenances.getTotalMaintenanceAmount());
            } catch (Exception exception) {
                exception.printStackTrace();
                log.error("Error to create flat maintenance records : {}", flat);
            }
        }
        log.info("Finished creating monthly maintenance records.");
    }

    @Override
    public List<Month> getAllMonths() {
        log.info("Request to get all month");
        return List.of(Month.values());
    }

    @Override
    public List<PaymentMode> getAllPaymentMode() {
        log.info("Request to get all PaymentMode");
        return List.of(PaymentMode.values());
    }

    @Override
    public List<MaintenanceStatusDTO> getAllMaintenanceStatus() {
        log.info("Request to get all MaintenanceStatus");
        return MaintenanceStatus.getVisible();
    }
}

package com.apartment.management.service.impl;

import com.apartment.management.entity.ApartmentBlockFlats;
import com.apartment.management.entity.FlatsMaintenances;
import com.apartment.management.exception.GlobalException;
import com.apartment.management.repository.ApartmentBlockFlatsRepository;
import com.apartment.management.repository.ApartmentsBlocksRepository;
import com.apartment.management.service.ApartmentBlockFlatsService;
import com.apartment.management.service.UserService;
import com.apartment.management.service.dto.ApartmentBlockFlatsDTO;
import com.apartment.management.service.dto.FlatOwnerDTO;
import com.apartment.management.service.mapper.ApartmentBlockFlatsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A ApartmentBlockFlatsServiceImpl
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ApartmentBlockFlatsServiceImpl implements ApartmentBlockFlatsService {

    // Lock map for per-flat locking
    private final ConcurrentHashMap<Long, ReentrantLock> flatLocks = new ConcurrentHashMap<>();

    private final ApartmentBlockFlatsRepository apartmentBlockFlatsRepository;

    private final ApartmentBlockFlatsMapper apartmentBlockFlatsMapper;

    private final UserService userService;

    private final ApartmentsBlocksRepository apartmentsBlocksRepository;

    @Override
    public ApartmentBlockFlats findById(Long id) {
        log.info("Request to find ApartmentBlockFlats Details by id : {} ", id);
        return apartmentBlockFlatsRepository.findById(id).orElseThrow(() -> new GlobalException("Flat details not found"));
    }

    @Override
    @Transactional
    public ApartmentBlockFlatsDTO save(ApartmentBlockFlatsDTO apartmentBlockFlatsDTO) {
        log.info("Request to save flat : {}", apartmentBlockFlatsDTO);
        FlatOwnerDTO owner = apartmentBlockFlatsDTO.getOwner();
        ApartmentBlockFlats apartmentBlockFlats = apartmentBlockFlatsMapper.toEntity(apartmentBlockFlatsDTO);
        if (owner != null && owner.getId() == null) {
            apartmentBlockFlats.setOwner(userService.addFlatOwner(owner));
        }
        apartmentBlockFlats = apartmentBlockFlatsRepository.save(apartmentBlockFlats);
        apartmentsBlocksRepository.findById(apartmentBlockFlatsDTO.getBlocksId())
                .ifPresent(apartmentsBlocks -> apartmentsBlocks.setNumberOfFlats(apartmentsBlocks.getNumberOfFlats() + 1));
        return apartmentBlockFlatsMapper.toDto(apartmentBlockFlats);
    }

    @Override
    public void updateFlatOwner(Long id, FlatOwnerDTO flatOwnerDTO, boolean isRemoveOwner) {
        log.info("Request to update flat owner. Flat id : {}, Owner : {} and isRemoveOwner : {}", id, flatOwnerDTO, isRemoveOwner);
        ApartmentBlockFlats apartmentBlockFlats = findById(id);
        if (isRemoveOwner) {
            apartmentBlockFlats.setOwner(null);
            apartmentBlockFlatsRepository.save(apartmentBlockFlats);
        } else {
            if (flatOwnerDTO != null) {
                apartmentBlockFlats.setOwner(userService.addFlatOwner(flatOwnerDTO));
                apartmentBlockFlatsRepository.save(apartmentBlockFlats);
            }
        }
    }

    @Override
    public ApartmentBlockFlatsDTO getFlatById(Long id) {
        log.info("Request to get flat by id : {}", id);
        return apartmentBlockFlatsMapper.toDto(findById(id));
    }

    @Override
    public void updateFlatPaidAndDueMaintenanceByFlatMaintenance(FlatsMaintenances flatsMaintenances) {
        log.info("Request to update Flat Paid and Due Maintenance by flatId : {}", flatsMaintenances.getFlat().getId());
        if (flatsMaintenances.getMaintenanceStatus() == null) {
            throw new GlobalException("Something went wrong");
        }
        if (flatsMaintenances.getFlat() == null || flatsMaintenances.getFlat().getId() == null) {
            throw new GlobalException("Invalid flat ID");
        }
        Long flatId = flatsMaintenances.getFlat().getId();
        ReentrantLock lock = flatLocks.computeIfAbsent(flatId, id -> new ReentrantLock());
        boolean acquired = false;
        try {
            acquired = lock.tryLock(5, TimeUnit.SECONDS);
            if (!acquired) {
                throw new GlobalException("Flat is currently being updated by another request. Please try again later.");
            }
            ApartmentBlockFlats apartmentBlockFlats = findById(flatId);
            Double totalAmount = flatsMaintenances.getTotalMaintenanceAmount();
            switch (flatsMaintenances.getMaintenanceStatus()) {
                case PAID:
                    apartmentBlockFlats.setPaidMaintenance(apartmentBlockFlats.getPaidMaintenance() + totalAmount);
                    apartmentBlockFlats.setDueMaintenance(apartmentBlockFlats.getDueMaintenance() - totalAmount);
                    break;
                case NOT_REQUIRED:
                    apartmentBlockFlats.setDueMaintenance(apartmentBlockFlats.getDueMaintenance() - totalAmount);
                    break;
                default:
                    throw new GlobalException("Unexpected maintenance status");
            }
            apartmentBlockFlatsRepository.save(apartmentBlockFlats);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new GlobalException("Thread was interrupted while waiting for flat lock");
        } finally {
            if (acquired) {
                lock.unlock();
                log.info("Lock released for Flat ID: {}", flatId);
                if (!lock.hasQueuedThreads()) {
                    flatLocks.remove(flatId, lock);
                    log.debug("Lock removed from map for Flat ID: {}", flatId);
                }
            }
        }
    }

    @Override
    public List<ApartmentBlockFlats> findAll() {
        log.info("Request to get All Flats");
        return apartmentBlockFlatsRepository.findAll();
    }
}

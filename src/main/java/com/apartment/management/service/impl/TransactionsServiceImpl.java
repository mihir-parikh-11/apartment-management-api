package com.apartment.management.service.impl;

import com.apartment.management.entity.Apartments;
import com.apartment.management.entity.FlatsMaintenances;
import com.apartment.management.entity.Transactions;
import com.apartment.management.entity.enums.TransactionType;
import com.apartment.management.repository.TransactionsRepository;
import com.apartment.management.service.ApartmentsService;
import com.apartment.management.service.TransactionsService;
import com.apartment.management.service.dto.MiscellaneousTransactionDTO;
import com.apartment.management.service.dto.TransactionsDTO;
import com.apartment.management.service.mapper.TransactionsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

/**
 * A TransactionsServiceImpl
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionsServiceImpl implements TransactionsService {

    private final TransactionsRepository transactionsRepository;

    private final TransactionsMapper transactionsMapper;

    private final ApartmentsService apartmentsService;

    @Override
    @Transactional
    public void saveMiscellaneousTransaction(MiscellaneousTransactionDTO miscellaneousTransactionDTO) {
        log.info("Request to save miscellaneous transactions : {}", miscellaneousTransactionDTO);
        Apartments apartments = apartmentsService.updateApartmentsAvailableByMiscellaneous(miscellaneousTransactionDTO);
        Transactions transaction = transactionsMapper.toEntity(miscellaneousTransactionDTO);
        transaction.setTransactionDate(Date.valueOf(LocalDate.now()));
        transaction.setApartments(apartments);
        transaction.setTransactionType(TransactionType.DEBIT);
        transaction.setMaintenanceAmount(false);
        transaction.setRemainingBalance(apartments.getAvailableAmount());
        transactionsRepository.save(transaction);
        log.info("Saving Miscellaneous Transaction: {}", transaction);
    }

    @Override
    public List<TransactionsDTO> getAllTransactionsByApartmentId(Long apartmentId) {
        log.info("Request to get all Transactions for Apartment by apartmentId : {}", apartmentId);
        return transactionsRepository.findAllByApartmentsIdOrderByTransactionDateDescIdDesc(apartmentId)
                .stream()
                .map(transactionsMapper::toTransactionsDTO)
                .toList();
    }

    @Override
    public List<TransactionsDTO> getAllTransactionsForFlatId(Long flatId) {
        log.info("Request to get all Transactions for Flat by flatId : {}", flatId);
        return transactionsRepository.findAllByFlatId(flatId)
                .stream()
                .map(transactionsMapper::toTransactionsDTO)
                .toList();
    }

    @Override
    public void addFlatMaintenanceTransaction(FlatsMaintenances flatsMaintenances, Apartments apartments) {
        log.info("Add Flat Maintenance transaction : {}", flatsMaintenances);
        String description = String.format("Flat Maintenance:- Flat number %s for %s-%s",
                flatsMaintenances.getFlat().getFlatNumber().strip(),
                Month.of(flatsMaintenances.getMaintenanceMonth()),
                flatsMaintenances.getMaintenanceYear());
        Transactions transaction = Transactions.builder()
                .apartments(apartments)
                .amount(flatsMaintenances.getTotalMaintenanceAmount())
                .description(description)
                .transactionType(TransactionType.CREDIT)
                .transactionDate(Date.valueOf(LocalDate.now()))
                .isMaintenanceAmount(true)
                .flatsMaintenances(flatsMaintenances)
                .remainingBalance(apartments.getAvailableAmount())
                .build();
        transactionsRepository.save(transaction);
        log.info("Saving Flat Maintenance Transaction: {}", transaction);
    }
}

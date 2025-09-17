package com.apartment.management.controller;

import com.apartment.management.service.TransactionsService;
import com.apartment.management.service.dto.MiscellaneousTransactionDTO;
import com.apartment.management.service.dto.TransactionsDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * A TransactionsController
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
@Slf4j
public class TransactionsController {

    private final TransactionsService transactionsService;

    /**
     * Save miscellaneous transactions
     *
     * @param miscellaneousTransactionDTO DTO
     * @return Void
     */
    @PostMapping("/miscellaneous")
    public ResponseEntity<Void> saveMiscellaneousTransaction(@RequestBody MiscellaneousTransactionDTO miscellaneousTransactionDTO) {
        log.info("REST request to save miscellaneous transactions : {}", miscellaneousTransactionDTO);
        transactionsService.saveMiscellaneousTransaction(miscellaneousTransactionDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * Get all Transactions for Apartment by id
     *
     * @param apartmentId apartmentId
     * @return list of DTO
     */
    @GetMapping("/apartment")
    public ResponseEntity<List<TransactionsDTO>> getAllTransactionsForApartment(@RequestParam(name = "apartmentId") Long apartmentId) {
        log.info("REST request to get all Transactions for Apartment by apartmentId : {}", apartmentId);
        return ResponseEntity.ok(transactionsService.getAllTransactionsByApartmentId(apartmentId));
    }

    /**
     * Get all Transactions for Flat by id
     *
     * @param flatId flatId
     * @return list of DTO
     */
    @GetMapping("/flat")
    public ResponseEntity<List<TransactionsDTO>> getAllTransactionsForFlat(@RequestParam(name = "flatId") Long flatId) {
        log.info("REST request to get all Transactions for Flat by flatId : {}", flatId);
        return ResponseEntity.ok(transactionsService.getAllTransactionsForFlatId(flatId));
    }
}


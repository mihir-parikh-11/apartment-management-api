package com.apartment.management.repository;

import com.apartment.management.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * A TransactionsRepository
 */
@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {

    /**
     * Get All Transactions By apartmentId
     *
     * @param apartmentId apartmentId
     * @return list of Entity
     */
    List<Transactions> findAllByApartmentsIdOrderByTransactionDateDescIdDesc(Long apartmentId);

    /**
     * Get All Transactions By flatId
     *
     * @param flatId flatId
     * @return list of Entity
     */
    @Query("SELECT t FROM Transactions t " +
            "JOIN t.flatsMaintenances fm " +
            "JOIN fm.flat abf " +
            "WHERE abf.id = :flatId " +
            "AND t.isMaintenanceAmount = true " +
            "ORDER BY t.transactionDate DESC, t.id DESC")
    List<Transactions> findAllByFlatId(@RequestParam("flatId") Long flatId);  // List<Transactions> findAllByFlatsMaintenances_Flat_IdAndMaintenanceAmountTrueOrderByTransactionDateDescIdDesc(Long flatId);
}

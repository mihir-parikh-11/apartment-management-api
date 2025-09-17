package com.apartment.management.entity;

import com.apartment.management.entity.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

/**
 * A Transactions Entity.
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
public class Transactions implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "apartments_id")
    private Apartments apartments;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @Column(name = "is_maintenance_amount")
    private boolean isMaintenanceAmount;

    @OneToOne
    @JoinColumn(name = "flats_maintenances_id")
    private FlatsMaintenances flatsMaintenances;

    @Column(name = "remaining_balance")
    private Double remainingBalance;
}


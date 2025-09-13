package com.apartment.management.entity;

import com.apartment.management.entity.enums.MaintenanceStatus;
import com.apartment.management.entity.enums.PaymentMode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A FlatsMaintenances Entity
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "flats_maintenances")
public class FlatsMaintenances implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "flat_id")
    private ApartmentBlockFlats flat;

    @Column(name = "maintenance_amount", nullable = false)
    private Double maintenanceAmount;

    @Column(name = "paid_amount")
    private Double paidAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "maintenance_status")
    private MaintenanceStatus maintenanceStatus = MaintenanceStatus.PENDING;

    @Column(name = "challan_number")
    private String challanNumber;

    @Column(name = "paid_date")
    private ZonedDateTime paidDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_mode")
    private PaymentMode paymentMode;

    @Column(name = "due_date", nullable = false)
    private ZonedDateTime dueDate;

    @Column(name = "due_amount")
    private Double dueAmount;

    @Column(name = "maintenance_month", nullable = false)
    private Integer maintenanceMonth;

    @Column(name = "maintenance_year", nullable = false)
    private Integer maintenanceYear;

    @Column(name = "total_maintenance_amount")
    private Double totalMaintenanceAmount;
}

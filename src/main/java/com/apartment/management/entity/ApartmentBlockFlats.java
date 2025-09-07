package com.apartment.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * An ApartmentBlockFlats Entity.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "apartment_block_flats")
public class ApartmentBlockFlats implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flat_number", nullable = false)
    private String flatNumber;

    @Column(name = "floor_number", nullable = false)
    private Integer floorNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartments_blocks_id")
    private ApartmentsBlocks apartmentsBlocks;

    @Column(name = "paid_maintenance", nullable = false)
    private Double paidMaintenance;

    @Column(name = "due_maintenance", nullable = false)
    private Double dueMaintenance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private User owner;

    @Override
    public String toString() {
        return "ApartmentBlockFlats{" +
                "id=" + id +
                ", flatNumber='" + flatNumber + '\'' +
                ", paidMaintenance=" + paidMaintenance +
                ", dueMaintenance=" + dueMaintenance +
                ", owner=" + owner +
                '}';
    }
}

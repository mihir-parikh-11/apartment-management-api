package com.apartment.management.entity;

import com.apartment.management.entity.enums.ApartmentsStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * An Apartments Entity.
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "apartments")
public class Apartments implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "number_of_blocks")
    private Integer numberOfBlocks;

    @Column(name = "available_amount")
    private Double availableAmount;

    @Column(name = "due_amount")
    private Double dueAmount;

    @Column(name = "per_flat_maintenance")
    private Double perFlatMaintenance;

    @Column(name = "maintenance_due_days")
    private Integer maintenanceDueDays;

    @Column(name = "charges_per_day_due")
    private Double chargesPerDayDue;

    @Column(name = "address_1")
    private String address1;

    @Column(name = "address_2")
    private String address2;

    @Column(name = "city")
    private String city;

    @Column(name = "postcode")
    private String postcode;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;

    @Column(name = "status")
    private Boolean status = Boolean.TRUE;

    @Enumerated(EnumType.STRING)
    @Column(name = "apartments_status")
    private ApartmentsStatus apartmentsStatus = ApartmentsStatus.PENDING;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;

    @Column(name = "created_date", updatable = false, nullable = false)
    @CreationTimestamp
    private ZonedDateTime createdDate;

    @OneToMany(mappedBy = "apartments", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<ApartmentsBlocks> blocks;

    @Transient
    private Integer totalFlats;

    public Integer getTotalFlats() {
        if (blocks == null)
            return 0;
        return totalFlats = blocks.stream()
                .mapToInt(ApartmentsBlocks::getNumberOfFlats)
                .sum();
    }

    @Override
    public String toString() {
        return "Apartments{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberOfBlocks=" + numberOfBlocks +
                ", availableAmount=" + availableAmount +
                ", dueAmount=" + dueAmount +
                ", perFlatMaintenance=" + perFlatMaintenance +
                ", maintenanceDueDays=" + maintenanceDueDays +
                ", chargesPerDayDue=" + chargesPerDayDue +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", postcode='" + postcode + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", manager=" + manager +
                ", status=" + status +
                ", apartmentsStatus=" + apartmentsStatus +
                ", createdBy=" + createdBy +
                ", createdDate=" + createdDate +
                '}';
    }
}

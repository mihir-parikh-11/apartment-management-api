package com.apartment.management.repository;

import com.apartment.management.entity.FlatsMaintenances;
import com.apartment.management.entity.enums.MaintenanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A FlatsMaintenancesRepository
 */
@Repository
public interface FlatsMaintenancesRepository extends JpaRepository<FlatsMaintenances, Long> {

    @Query("SELECT fm FROM FlatsMaintenances fm " +
            "WHERE fm.flat.id = :flatId " +
            "AND (:status IS NULL OR fm.maintenanceStatus = :status) " +
            "AND fm.maintenanceYear = :year")
    List<FlatsMaintenances> findAllByFlatIdAndOptionalStatusAndYear(@Param("flatId") Long flatId,
                                                                    @Param("status") MaintenanceStatus status,
                                                                    @Param("year") Integer year);

    @Query("""
            SELECT fm FROM FlatsMaintenances fm
                  JOIN fm.flat af
                  JOIN af.apartmentsBlocks ab
                  JOIN ab.apartments ap
                  WHERE ap.id = :apartmentId
                    AND (:status IS NULL OR fm.maintenanceStatus = :status)
                    AND (:month IS NULL OR fm.maintenanceMonth = :month)
                    AND fm.maintenanceYear = :year
                  ORDER BY af.floorNumber, af.flatNumber
            """)
    List<FlatsMaintenances> findAllByApartmentIdAndOptionalStatusAndOptionalMonthAndYear(@Param("apartmentId") Long apartmentId,
                                                                                         @Param("status") MaintenanceStatus maintenanceStatus,
                                                                                         @Param("month") Integer month,
                                                                                         @Param("year") Integer year);
}

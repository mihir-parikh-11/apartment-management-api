package com.apartment.management.repository;

import com.apartment.management.entity.ApartmentTickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A ApartmentTicketsRepository
 */
@Repository
public interface ApartmentTicketsRepository extends JpaRepository<ApartmentTickets, Long> {

    /**
     * Get ALl ApartmentTickets By apartmentId
     * Order By createdDate and id
     *
     * @param apartmentId apartmentId
     * @return list of Entity
     */
    List<ApartmentTickets> findAllByApartmentsIdOrderByCreatedDateDescIdDesc(Long apartmentId);

    /**
     * Get count of ticket status
     *
     * @return Object[]
     */
    @Query("SELECT at.ticketStatus, COUNT(at) FROM ApartmentTickets at GROUP BY at.ticketStatus")
    List<Object[]> countOfTickets();
}

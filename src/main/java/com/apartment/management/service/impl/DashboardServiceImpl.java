package com.apartment.management.service.impl;

import com.apartment.management.entity.Apartments;
import com.apartment.management.entity.enums.ApartmentsStatus;
import com.apartment.management.entity.enums.TicketStatus;
import com.apartment.management.repository.ApartmentTicketsRepository;
import com.apartment.management.repository.ApartmentsRepository;
import com.apartment.management.repository.UserRepository;
import com.apartment.management.service.DashboardService;
import com.apartment.management.service.dto.DashboardDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A DashboardServiceImpl
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DashboardServiceImpl implements DashboardService {

    private final UserRepository userRepository;

    private final ApartmentsRepository apartmentsRepository;

    private final ApartmentTicketsRepository apartmentTicketsRepository;

    @Override
    public DashboardDTO getDashboardData() {
        log.info("Request get dashboard data");
        DashboardDTO dashboardDTO = DashboardDTO.builder().build();
        countOfUsers(dashboardDTO);
        countOfApartments(dashboardDTO);
        countOfTickets(dashboardDTO);
        return dashboardDTO;
    }

    private void countOfTickets(DashboardDTO dashboardDTO) {
        long totalTickets = 0;
        long totalPendingTickets = 0;
        long totalInProgressTickets = 0;
        long totalInReviewTickets = 0;
        long totalResolvedTickets = 0;
        long totalCancelTickets = 0;
        List<Object[]> ticketCounts = apartmentTicketsRepository.countOfTickets();
        for (Object[] ticketCount : ticketCounts) {
            TicketStatus ticketStatus = (TicketStatus) ticketCount[0];
            long count = (Long) ticketCount[1];
            switch (ticketStatus) {
                case PENDING -> totalPendingTickets = count;
                case IN_PROGRESS -> totalInProgressTickets = count;
                case IN_REVIEW -> totalInReviewTickets = count;
                case RESOLVED -> totalResolvedTickets = count;
                case CANCEL -> totalCancelTickets = count;
            }
            totalTickets += count;
        }
        dashboardDTO.setTotalTickets(totalTickets);
        dashboardDTO.setTotalPendingTickets(totalPendingTickets);
        dashboardDTO.setTotalInProgressTickets(totalInProgressTickets);
        dashboardDTO.setTotalInReviewTickets(totalInReviewTickets);
        dashboardDTO.setTotalResolvedTickets(totalResolvedTickets);
        dashboardDTO.setTotalCancelTickets(totalCancelTickets);
    }

    private void countOfApartments(DashboardDTO dashboardDTO) {
        List<Apartments> apartmentsList = apartmentsRepository.findAll();
        long totalApartments = apartmentsList.size();
        long totalPendingApartments = apartmentsList.stream().filter(apartments -> apartments.getApartmentsStatus() == ApartmentsStatus.PENDING).count();
        long totalApprovedApartments = apartmentsList.stream().filter(apartments -> apartments.getApartmentsStatus() == ApartmentsStatus.APPROVED).count();
        long totalRejectedApartments = apartmentsList.stream().filter(apartments -> apartments.getApartmentsStatus() == ApartmentsStatus.REJECTED).count();
        long totalBlocks = apartmentsList.stream().mapToInt(Apartments::getNumberOfBlocks).sum();
        long totalFlats = apartmentsList.stream().mapToInt(Apartments::getTotalFlats).sum();
        dashboardDTO.setTotalApartments(totalApartments);
        dashboardDTO.setTotalPendingApartments(totalPendingApartments);
        dashboardDTO.setTotalApprovedApartments(totalApprovedApartments);
        dashboardDTO.setTotalRejectedApartments(totalRejectedApartments);
        dashboardDTO.setTotalBlocks(totalBlocks);
        dashboardDTO.setTotalFlats(totalFlats);
    }

    private void countOfUsers(DashboardDTO dashboardDTO) {
        long totalUsers = 0;
        long totalManagers = 0;
        long totalOwners = 0;
        long totalAdmins = 0;
        List<Object[]> roleCounts = userRepository.countUsersByRole();
        for (Object[] roleCount : roleCounts) {
            String role = (String) roleCount[0];
            long count = (Long) roleCount[1];
            switch (role) {
                case "ROLE_ADMIN":
                    totalAdmins = count;
                    break;
                case "ROLE_MANAGER":
                    totalManagers = count;
                    break;
                case "ROLE_USER":
                    totalOwners = count;
                    break;
                default:
                    break;
            }
            totalUsers += count;
        }
        dashboardDTO.setTotalUsers(totalUsers);
        dashboardDTO.setTotalManagers(totalManagers);
        dashboardDTO.setTotalOwners(totalOwners);
        dashboardDTO.setTotalAdmins(totalAdmins);
    }
}

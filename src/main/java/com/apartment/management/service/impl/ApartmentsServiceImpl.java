package com.apartment.management.service.impl;

import com.apartment.management.entity.Apartments;
import com.apartment.management.entity.User;
import com.apartment.management.entity.enums.ApartmentsStatus;
import com.apartment.management.exception.GlobalException;
import com.apartment.management.repository.ApartmentsRepository;
import com.apartment.management.service.ApartmentsService;
import com.apartment.management.service.UserService;
import com.apartment.management.service.dto.ApartmentsRequestDTO;
import com.apartment.management.service.dto.ApartmentsResponseDTO;
import com.apartment.management.service.mapper.ApartmentsMapper;
import com.apartment.management.util.UserUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * A ApartmentsServiceImpl
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ApartmentsServiceImpl implements ApartmentsService {

    private final ApartmentsRepository apartmentsRepository;

    private final ApartmentsMapper apartmentsMapper;

    private final UserService userService;

    @Override
    public Apartments findById(Long id) {
        log.info("Get Apartments Entity by id : {} ", id);
        return apartmentsRepository.findById(id).orElseThrow(() -> new GlobalException("Apartments not found by id"));
    }

    @Override
    public ApartmentsResponseDTO saveApartments(ApartmentsRequestDTO apartmentsRequestDTO) {
        log.info("Request to save apartments : {}", apartmentsRequestDTO);
        Apartments apartments = apartmentsMapper.toEntity(apartmentsRequestDTO);
        User currentLoginUser = UserUtility.getCurrentLoginUser();
        apartments.setManager(currentLoginUser);
        apartments.setCreatedBy(currentLoginUser);
        apartments.setApartmentsStatus(ApartmentsStatus.PENDING);
        apartments.setStatus(Boolean.TRUE);
        apartments.setDueAmount(0d);
        apartments.setNumberOfBlocks(0);
        return apartmentsMapper.toResponseDto(apartmentsRepository.save(apartments));
    }

    @Override
    public ApartmentsResponseDTO updateApartments(ApartmentsRequestDTO apartmentsRequestDTO) {
        Apartments apartments = findById(apartmentsRequestDTO.getId());
        apartmentsMapper.updateEntityFromDto(apartmentsRequestDTO, apartments);
        return null;
    }

    @Override
    public void updateApartmentsStatus(Long id, Boolean status) {
        log.info("Request to update Apartments : {}, Status : {}", id, status);
        Apartments apartments = findById(id);
        apartments.setStatus(status);
    }

    @Override
    public void updateApartmentsApprovalStatus(Long id, ApartmentsStatus status) {
        log.info("Request to update Apartments : {}, Approval Status : {}", id, status);
        Apartments apartments = findById(id);
        apartments.setApartmentsStatus(status);
    }

    @Override
    public ApartmentsResponseDTO getApartmentsById(Long id) {
        log.info("Request to get Apartments by id : {}", id);
        Apartments apartments = findById(id);
        return apartmentsMapper.toResponseDto(apartments);
    }

    @Override
    public List<ApartmentsResponseDTO> getAllApartments() {
        log.info("Request to get All Apartments");
        User currentLoginUser = UserUtility.getCurrentLoginUser();
        if (currentLoginUser.getRoleNames().contains("ROLE_ADMIN"))
            return apartmentsRepository.findAll().stream().map(apartmentsMapper::toResponseDto).toList();
        else {
            return apartmentsRepository.findAllByManagerId(currentLoginUser.getId()).stream().map(apartmentsMapper::toResponseDto).toList();
        }
    }

    @Override
    public void updateApartmentsManager(Long id, Long managerId, boolean isRemoveManger) {
        log.info("Request to update Apartment Manager by apartmentId : {} and managerId : {}", id, managerId);
        Apartments apartments = findById(id);
        if (managerId != null && !isRemoveManger)
            apartments.setManager(userService.findById(id));
        else
            apartments.setManager(null);
    }

    @Override
    public List<ApartmentsResponseDTO> getAllOwnerApartment() {
        log.info("Request to get all owner Apartments");
        return apartmentsRepository.findAllDistinctApartmentsByOwnerId(UserUtility.getCurrentLoginUser().getId())
                .stream()
                .map(apartmentsMapper::toResponseDto)
                .toList();
    }
}

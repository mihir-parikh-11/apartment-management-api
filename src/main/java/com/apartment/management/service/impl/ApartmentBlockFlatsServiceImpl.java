package com.apartment.management.service.impl;

import com.apartment.management.entity.ApartmentBlockFlats;
import com.apartment.management.exception.GlobalException;
import com.apartment.management.repository.ApartmentBlockFlatsRepository;
import com.apartment.management.repository.ApartmentsBlocksRepository;
import com.apartment.management.service.ApartmentBlockFlatsService;
import com.apartment.management.service.UserService;
import com.apartment.management.service.dto.ApartmentBlockFlatsDTO;
import com.apartment.management.service.dto.FlatOwnerDTO;
import com.apartment.management.service.mapper.ApartmentBlockFlatsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * A ApartmentBlockFlatsServiceImpl
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ApartmentBlockFlatsServiceImpl implements ApartmentBlockFlatsService {

    private final ApartmentBlockFlatsRepository apartmentBlockFlatsRepository;

    private final ApartmentBlockFlatsMapper apartmentBlockFlatsMapper;

    private final UserService userService;

    private final ApartmentsBlocksRepository apartmentsBlocksRepository;

    @Override
    public ApartmentBlockFlats findById(Long id) {
        log.info("Request to find ApartmentBlockFlats Details by id : {} ", id);
        return apartmentBlockFlatsRepository.findById(id).orElseThrow(() -> new GlobalException("Flat details not found"));
    }

    @Override
    @Transactional
    public ApartmentBlockFlatsDTO save(ApartmentBlockFlatsDTO apartmentBlockFlatsDTO) {
        log.info("Request to save flat : {}", apartmentBlockFlatsDTO);
        FlatOwnerDTO owner = apartmentBlockFlatsDTO.getOwner();
        ApartmentBlockFlats apartmentBlockFlats = apartmentBlockFlatsMapper.toEntity(apartmentBlockFlatsDTO);
        if (owner != null && owner.getId() == null) {
            apartmentBlockFlats.setOwner(userService.addFlatOwner(owner));
        }
        apartmentBlockFlats = apartmentBlockFlatsRepository.save(apartmentBlockFlats);
        apartmentsBlocksRepository.findById(apartmentBlockFlatsDTO.getBlocksId())
                .ifPresent(apartmentsBlocks -> apartmentsBlocks.setNumberOfFlats(apartmentsBlocks.getNumberOfFlats() + 1));
        return apartmentBlockFlatsMapper.toDto(apartmentBlockFlats);
    }

    @Override
    public void updateFlatOwner(Long id, FlatOwnerDTO flatOwnerDTO, boolean isRemoveOwner) {
        log.info("Request to update flat owner. Flat id : {}, Owner : {} and isRemoveOwner : {}", id, flatOwnerDTO, isRemoveOwner);
        ApartmentBlockFlats apartmentBlockFlats = findById(id);
        if (isRemoveOwner) {
            apartmentBlockFlats.setOwner(null);
            apartmentBlockFlatsRepository.save(apartmentBlockFlats);
        } else {
            if (flatOwnerDTO != null) {
                apartmentBlockFlats.setOwner(userService.addFlatOwner(flatOwnerDTO));
                apartmentBlockFlatsRepository.save(apartmentBlockFlats);
            }
        }
    }

    @Override
    public ApartmentBlockFlatsDTO getFlatById(Long id) {
        log.info("Request to get flat by id : {}", id);
        return apartmentBlockFlatsMapper.toDto(findById(id));
    }
}

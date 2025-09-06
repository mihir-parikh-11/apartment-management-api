package com.apartment.management.service.impl;

import com.apartment.management.entity.ApartmentsBlocks;
import com.apartment.management.exception.GlobalException;
import com.apartment.management.repository.ApartmentsBlocksRepository;
import com.apartment.management.repository.ApartmentsRepository;
import com.apartment.management.service.ApartmentsBlocksService;
import com.apartment.management.service.dto.ApartmentsBlocksDTO;
import com.apartment.management.service.mapper.ApartmentsBlocksMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * A ApartmentsBlocksServiceImpl
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ApartmentsBlocksServiceImpl implements ApartmentsBlocksService {

    private final ApartmentsBlocksRepository apartmentsBlocksRepository;

    private final ApartmentsBlocksMapper apartmentsBlocksMapper;

    private final ApartmentsRepository apartmentsRepository;

    @Override
    @Transactional
    public ApartmentsBlocksDTO saveApartmentsBlocks(ApartmentsBlocksDTO apartmentsBlocksDTO) {
        log.info("Request to save ApartmentsBlocks : {} ", apartmentsBlocksDTO);
        ApartmentsBlocks apartmentsBlocks = apartmentsBlocksMapper.toEntity(apartmentsBlocksDTO);
        apartmentsBlocks.setNumberOfFlats(0);
        apartmentsBlocks = apartmentsBlocksRepository.save(apartmentsBlocks);
        apartmentsRepository.findById(apartmentsBlocks.getApartments().getId())
                .ifPresent(apartments -> apartments.setNumberOfBlocks(apartments.getNumberOfBlocks() + 1));
        return apartmentsBlocksMapper.toDto(apartmentsBlocks);
    }

    @Override
    @Transactional
    public ApartmentsBlocksDTO updateApartmentsBlocks(ApartmentsBlocksDTO apartmentsBlocksDTO) {
        log.info("Request to update ApartmentsBlocks : {} ", apartmentsBlocksDTO);
        ApartmentsBlocks apartmentsBlocks = findById(apartmentsBlocksDTO.getId());
        apartmentsBlocks.setBlockName(apartmentsBlocks.getBlockName());
        return apartmentsBlocksMapper.toDto(apartmentsBlocks);
    }

    @Override
    public ApartmentsBlocks findById(Long id) {
        log.info("ApartmentsBlocks find by id : {}", id);
        return apartmentsBlocksRepository.findById(id).orElseThrow(() -> new GlobalException("ApartmentsBlocks Not Found"));
    }

    @Override
    public ApartmentsBlocksDTO getApartmentsBlocksById(Long id) {
        log.info("Request to get ApartmentsBlocks by id : {}", id);
        return apartmentsBlocksMapper.toDto(findById(id));
    }

    @Override
    public List<ApartmentsBlocksDTO> getAllApartmentsBlocksByApartmentId(Long apartmentId) {
        log.info("Request to get All ApartmentsBlocks by apartmentId : {}", apartmentId);
        return apartmentsBlocksMapper.toDto(apartmentsBlocksRepository.findAllByApartmentsId(apartmentId));
    }
}

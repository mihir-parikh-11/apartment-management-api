package com.apartment.management.service.mapper.config;

import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * A GenericMapper
 *
 * @param <E> entity
 * @param <D> DTO
 */
public interface GenericMapper<E, D> {

    E toEntity(D dto);

    D toDto(E entity);

    void updateEntityFromDto(D dto, @MappingTarget E entity);

    List<D> toDto(List<E> entities);

    List<E> toEntity(List<D> dtos);
}

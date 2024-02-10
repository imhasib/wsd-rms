package com.hsb.rms.service.mapper;

import java.util.List;
/**
 * @param <D> - DTO.
 * @param <E> - Entity
 */
public interface DtoMapper<D, E> {
    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List<D> toDto(List<E> entityList);
}

package com.hsb.rms.service;

import com.hsb.rms.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface OrderService {

    /**
     * Save the orderDto.
     *
     * @param orderDto the orderDto to save.
     * @return the persisted orderDto.
     */
    OrderDto save(OrderDto orderDto);

    /**
     * Get all the orderDtos.
     *
     * @param pageable the pagination information.
     * @return the list of orderDtos.
     */
    Page<OrderDto> findAll(Pageable pageable);

    /**
     * Get the orderDto by Id.
     *
     * @param id the id of the orderDto.
     * @return the orderDto.
     */
    Optional<OrderDto> findOne(Long id);
}

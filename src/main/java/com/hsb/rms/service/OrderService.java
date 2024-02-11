package com.hsb.rms.service;

import com.hsb.rms.domain.Order;
import com.hsb.rms.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;
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

    /**
     * Get all the orderDtos of today.
     *
     * @return the list of orderDtos.
     */
    public List<OrderDto> findAllOrdersBetweenDates(Instant startDate, Instant endDate);

    /**
     * Get all the orderDtos of a customer.
     *
     * @return the list of orderDtos.
     */
    public List<OrderDto> findAllOrdersByUser(Long userId);
}

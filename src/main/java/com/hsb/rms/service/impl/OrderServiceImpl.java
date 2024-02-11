package com.hsb.rms.service.impl;

import com.hsb.rms.domain.Order;
import com.hsb.rms.dto.OrderDto;
import com.hsb.rms.repository.OrderRepository;
import com.hsb.rms.service.OrderService;
import com.hsb.rms.service.SaleService;
import com.hsb.rms.service.mapper.OrderMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;
    private final SaleService saleService;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, SaleService saleService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.saleService = saleService;
    }

    @Override
    public OrderDto save(OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);
        order = orderRepository.save(order);
        return orderMapper.toDto(order);
    }
    @Override
    public Page<OrderDto> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable).map(orderMapper::toDto);
    }

    @Override
    public Optional<OrderDto> findOne(Long id) {
        return orderRepository.findOneWithEagerRelationships(id).map(orderMapper::toDto);
    }
}
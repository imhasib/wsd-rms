package com.hsb.rms.controller;

import com.hsb.rms.dto.OrderDto;
import com.hsb.rms.exception.InvalidInputException;
import com.hsb.rms.repository.OrderRepository;
import com.hsb.rms.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    private final OrderRepository orderRepository;

    public OrderController(OrderService orderService, OrderRepository orderRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    /**
     * {@code GET  /orders} : get all the orders of specific date range.
     *
     * @param startDate the start of a specific date.
     * @param endDate the end of a specific date.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orders in body.
     */
    @GetMapping("/by/date")
    public ResponseEntity<List<OrderDto>> getAllOrders(
        @RequestParam(name = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Instant startDate,
        @RequestParam(name = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Instant endDate) {
        if (startDate == null || endDate == null) {
            LocalDate currentDate = LocalDate.now();
            startDate = currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
            endDate = currentDate.plusDays(1).atStartOfDay(ZoneId.systemDefault()).minusNanos(1).toInstant();
        }

        List<OrderDto> orderDtos = orderService.findAllOrdersBetweenDates(startDate, endDate);
        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }


    /**
     * {@code GET  /orders} : get all the orders of specific user.
     *
     * @param userId the id of the user.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orders in body.
     */
    @GetMapping("/by/user/{userId}")
    public ResponseEntity<List<OrderDto>> getAllOrders(@PathVariable("userId") Long userId) {
        List<OrderDto> orderDtos = orderService.findAllOrdersByUser(userId);
        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }


    /**
     * {@code POST  /orders} : Create a new order.
     *
     * @param orderDTO the orderDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orderDTO, or with status {@code 400 (Bad Request)} if the order has already an ID.
     */
    @PostMapping("")
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto orderDTO) {
        if (orderDTO.getId() != null) {
            throw new InvalidInputException("id");
        }
        OrderDto result = orderService.save(orderDTO);
        return ResponseEntity.ok(result);
    }

    /**
     * {@code GET  /orders} : get all the orders.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orders in body.
     */
    @GetMapping("")
    public ResponseEntity<Page<OrderDto>> getAllOrders(Pageable pageable) {
        Page<OrderDto> page = orderService.findAll(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    /**
     * {@code GET  /orders/:id} : get the "id" order.
     *
     * @param id the id of the orderDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orderDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable("id") Long id) {
        Optional<OrderDto> orderDTO = orderService.findOne(id);
        return ResponseEntity.ok(orderDTO.get());
    }

}

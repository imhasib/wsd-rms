package com.hsb.rms.controller;

import com.hsb.rms.dto.MaxSaleDayDto;
import com.hsb.rms.dto.OrderDto;
import com.hsb.rms.dto.TotalDto;
import com.hsb.rms.service.SaleService;
import com.hsb.rms.utils.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    /**
     * {@code GET  /api/sales/total/amount} : get total sale amount of the currentDate.
     *
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the total amount.
     */
    @GetMapping("/total/amount")
    public ResponseEntity<TotalDto> getTotalAmount(
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Instant startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Instant endDate) {
        if (startDate == null || endDate == null) {
            startDate = DateUtils.startOfTheCurrentDay();
            endDate = DateUtils.endOfTheCurrentDay();
        }
        Double total = saleService.totalSale(startDate, endDate);
        return new ResponseEntity<>(new TotalDto(total), HttpStatus.OK);
    }


    /**
     * {@code GET  /api/sales/max/sale-day} : get total sale amount of the currentDate.
     *
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the total amount.
     */
    @GetMapping("/max/sale-day")
    public ResponseEntity<MaxSaleDayDto> getMaxSaleDay(
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Instant startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Instant endDate) throws ParseException {
        if (startDate == null || endDate == null) {
            startDate = DateUtils.startOfTheCurrentDay();
            endDate = DateUtils.endOfTheCurrentDay();
        }
        MaxSaleDayDto dto = saleService.getMaxSaleDay(startDate, endDate);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}

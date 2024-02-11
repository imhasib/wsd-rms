package com.hsb.rms.service;

import com.hsb.rms.domain.Sale;
import com.hsb.rms.dto.MaxSaleDayDto;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.util.List;

@Service
public interface SaleService {
    /**
     * @param sale the orderDto to save.
     * @return the persisted sale.
     */
    Sale save(Sale sale);

    /**
     * @param startDate
     * @param endDate
     * @return total sale amount.
     */
    Double totalSale(Instant startDate, Instant endDate);
    /**
     * @param startDate
     * @param endDate
     * @return Maximum sale day of specific date range.
     */
    MaxSaleDayDto getMaxSaleDay(Instant startDate, Instant endDate) throws ParseException;
}

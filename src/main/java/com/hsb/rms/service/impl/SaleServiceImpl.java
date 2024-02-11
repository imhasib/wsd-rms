package com.hsb.rms.service.impl;

import com.hsb.rms.domain.Sale;
import com.hsb.rms.dto.MaxSaleDayDto;
import com.hsb.rms.repository.SaleRepository;
import com.hsb.rms.service.CustomQueryExecutor;
import com.hsb.rms.service.SaleService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final CustomQueryExecutor customQueryExecutor;

    public SaleServiceImpl(SaleRepository saleRepository, CustomQueryExecutor customQueryExecutor) {
        this.saleRepository = saleRepository;
        this.customQueryExecutor = customQueryExecutor;
    }

    @Override
    public Sale save(Sale sale) {
        return saleRepository.save(sale);
    }

    public Double totalSale(Instant startDate, Instant endDate) {
        return saleRepository.getTotalSaleAmountForDateRange(startDate, endDate);
    }

    public MaxSaleDayDto getMaxSaleDay(Instant startDate, Instant endDate) throws ParseException {
        StringBuffer queryString = new StringBuffer();
        queryString.append(" SELECT");
        queryString.append(" EXTRACT(DAY FROM s.created_date) AS d,");
        queryString.append(" EXTRACT(MONTH FROM s.created_date) AS mnth,");
        queryString.append(" EXTRACT(YEAR FROM s.created_date) AS yr,");
        queryString.append(" SUM(unit_price*quantity) AS tp");
        queryString.append(" FROM t_sale AS s");
        queryString.append(" WHERE s.created_date BETWEEN ? AND ?");
        queryString.append(" GROUP BY d, mnth, yr");
        queryString.append(" ORDER BY tp DESC");
        queryString.append(" LIMIT 1");

        List<Object[]> result = customQueryExecutor.executeNativeSQL(queryString.toString(), startDate, endDate);

        if (!result.isEmpty()) {
            Object[] row = result.get(0);
            int day = ((BigDecimal) row[0]).intValue();
            int month = ((BigDecimal) row[1]).intValue();
            int year = ((BigDecimal) row[2]).intValue();
            Double total = (Double) row[3];

            Instant date = new SimpleDateFormat("yyyy-mm-dd").parse(day+"-"+month+"-"+year).toInstant();
            return new MaxSaleDayDto(date, total);
        } else {
            return null;
        }
    }
}

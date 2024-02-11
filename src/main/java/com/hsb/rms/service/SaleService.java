package com.hsb.rms.service;

import com.hsb.rms.domain.Sale;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SaleService {
    /**
     * @param sale the orderDto to save.
     * @return the persisted sale.
     */
    Sale save(Sale sale);
}

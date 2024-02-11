package com.hsb.rms.service.impl;

import com.hsb.rms.domain.Sale;
import com.hsb.rms.repository.SaleRepository;
import com.hsb.rms.service.SaleService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;

    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public Sale save(Sale sale) {
        return saleRepository.save(sale);
    }
}

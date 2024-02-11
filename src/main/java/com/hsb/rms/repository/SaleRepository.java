package com.hsb.rms.repository;

import com.hsb.rms.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT SUM(s.quantity*s.unitPrice) FROM Sale s WHERE s.createdDate BETWEEN :startDate AND :endDate")
    Double getTotalSaleAmountForDateRange(@Param("startDate") Instant startDate, @Param("endDate") Instant endDate);
}

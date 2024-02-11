package com.hsb.rms.repository;

import com.hsb.rms.domain.Order;
import com.hsb.rms.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select order from Order order where order.customer = :customer")
    List<Order> findByCustomerIsCurrentUser(User customer);

    default Optional<Order> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }
    @Query("select order from Order order left join fetch order.customer left join fetch order.servedBy where order.id =:id")
    Optional<Order> findOneWithToOneRelationships(@Param("id") Long id);

    @Query("SELECT o FROM Order o WHERE o.createdDate BETWEEN :startDate AND :endDate")
    List<Order> findAllOrdersBetweenDates(@Param("startDate") Instant startDate, @Param("endDate") Instant endDate);
}

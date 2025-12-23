package org.threepixeldev.saungeraadmin.shared.data.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.threepixeldev.saungeraadmin.features.dashboard.dto.ChartDataResponse;
import org.threepixeldev.saungeraadmin.shared.data.model.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
    List<Order> findByPromotionId(Long promotionId);
    List<Order> findByOrderedDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Order> findByUserIdAndOrderedDateBetween(Long userId, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT SUM(o.totalPrice) FROM Order o WHERE o.deletedAt IS NULL")
    BigDecimal getTotalSales();

    @Query("SELECT COUNT(o) FROM Order o WHERE o.orderedDate >= :startDate AND o.deletedAt IS NULL")
    Long countNewOrders(@Param("startDate") LocalDateTime startDate);

    @Query("SELECT AVG(o.totalPrice) FROM Order o WHERE o.deletedAt IS NULL")
    BigDecimal getAvgOrderValue();

    @Query("SELECT new org.threepixeldev.saungeraadmin.features.dashboard.dto.ChartDataResponse(" +
            "CAST(FUNCTION('DATE_FORMAT', o.orderedDate, '%b %d') AS String), SUM(o.totalPrice)) " +
            "FROM Order o " +
            "WHERE o.orderedDate >= :startDate AND o.deletedAt IS NULL " +
            "GROUP BY FUNCTION('DATE_FORMAT', o.orderedDate, '%b %d') " +
            "ORDER BY MIN(o.orderedDate) ASC")
    List<ChartDataResponse> getRevenueChartData(@Param("startDate") LocalDateTime startDate);
}

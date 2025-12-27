package org.threepixeldev.saungeraadmin.shared.data.repository.jpa;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.threepixeldev.saungeraadmin.features.dashboard.dto.TopProductResponse;
import org.threepixeldev.saungeraadmin.shared.data.model.OrderItem;

import java.util.List;

@Repository
public interface OrderItemJpaRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderId(Long orderId);
    List<OrderItem> findByProductId(Long productId);
    void deleteByOrderId(Long orderId);

    @Query("SELECT new org.threepixeldev.saungeraadmin.features.dashboard.dto.TopProductResponse(" +
            "p.name, CAST(0 AS java.math.BigDecimal), SUM(oi.quantity), " +
            "'N/A') " +
            "FROM OrderItem oi " +
            "JOIN oi.product p " +
            "GROUP BY p.id, p.name " +
            "ORDER BY SUM(oi.quantity) DESC")
    List<TopProductResponse> findTopSellingProducts(Pageable pageable);
}

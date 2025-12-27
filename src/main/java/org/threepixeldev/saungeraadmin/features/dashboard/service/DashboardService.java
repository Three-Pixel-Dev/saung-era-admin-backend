package org.threepixeldev.saungeraadmin.features.dashboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.threepixeldev.saungeraadmin.features.dashboard.dto.*;
import org.threepixeldev.saungeraadmin.shared.data.repository.jpa.*;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final OrderJpaRepository orderRepository;
    private final UserJpaRepository userRepository;
    private final OrderItemJpaRepository orderItemRepository;

    @Transactional(readOnly = true)
    public DashboardStatsResponse getStats() {
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);

        long activeUsers = userRepository.count(); 

        return DashboardStatsResponse.builder()
                .totalSales(orderRepository.getTotalSales())
                .newOrders(orderRepository.countNewOrders(thirtyDaysAgo))
                .activeUsers(activeUsers)
                .avgOrderValue(orderRepository.getAvgOrderValue())
                .build();
    }

    @Transactional(readOnly = true)
    public List<ChartDataResponse> getRevenueChart() {
        return orderRepository.getRevenueChartData(LocalDateTime.now().minusDays(30));
    }

    @Transactional(readOnly = true)
    public List<TopProductResponse> getTopProducts() {
        return orderItemRepository.findTopSellingProducts(PageRequest.of(0, 5));
    }
}

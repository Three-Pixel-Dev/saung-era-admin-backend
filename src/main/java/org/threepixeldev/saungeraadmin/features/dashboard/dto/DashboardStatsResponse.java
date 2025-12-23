package org.threepixeldev.saungeraadmin.features.dashboard.dto;

import lombok.Builder;
import java.math.BigDecimal;

@Builder
public record DashboardStatsResponse (
        BigDecimal totalSales,
        Long newOrders,
        Long activeUsers,
        BigDecimal avgOrderValue
) {}

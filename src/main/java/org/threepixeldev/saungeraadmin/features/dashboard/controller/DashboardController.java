package org.threepixeldev.saungeraadmin.features.dashboard.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.threepixeldev.saungeraadmin.features.dashboard.dto.*;
import org.threepixeldev.saungeraadmin.features.dashboard.service.DashboardService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/dashboard")
@RequiredArgsConstructor
@Tag(name = "Dashboard", description = "Dashboard Analytics APIs")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/stats")
    @Operation(summary = "Get top card statistics (Total Sales, Active Users, etc.)")
    public ResponseEntity<DashboardStatsResponse> getStats() {
        return ResponseEntity.ok(dashboardService.getStats());
    }

    @GetMapping("/chart/revenue")
    @Operation(summary = "Get revenue data for the graph (Last 30 days)")
    public ResponseEntity<List<ChartDataResponse>> getRevenueChart() {
        return ResponseEntity.ok(dashboardService.getRevenueChart());
    }

    @GetMapping("/products/top")
    @Operation(summary = "Get top 5 best-selling products")
    public ResponseEntity<List<TopProductResponse>> getTopProducts() {
        return ResponseEntity.ok(dashboardService.getTopProducts());
    }
}

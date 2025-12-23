package org.threepixeldev.saungeraadmin.features.dashboard.dto;

import lombok.Builder;
import java.math.BigDecimal;

@Builder
public record TopProductResponse (
        String name,
        BigDecimal price,
        Long soldCount,
        String stockStatus
) {}

package org.threepixeldev.saungeraadmin.features.dashboard.dto;

import lombok.Builder;
import java.math.BigDecimal;

@Builder
public record ChartDataResponse (
        String date,// Format: "Aug 01"
        BigDecimal revenue
) {}

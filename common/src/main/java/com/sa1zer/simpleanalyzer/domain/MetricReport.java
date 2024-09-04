package com.sa1zer.simpleanalyzer.domain;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MetricReport(
        LocalDateTime date,
        Double usableSpace,
        Double totalSpace,
        Double jvmUsed,
        Double jvmMax,
        Double uptime
) {
}

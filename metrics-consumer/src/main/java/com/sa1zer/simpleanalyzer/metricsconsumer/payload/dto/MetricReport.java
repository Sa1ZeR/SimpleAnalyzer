package com.sa1zer.simpleanalyzer.metricsconsumer.payload.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MetricReport(
        @JsonFormat(pattern = "yyyy.MM.dd hh:mm:ss", shape = JsonFormat.Shape.STRING) LocalDateTime date,
        double usableSpace,
        double totalSpace,
        double usedMemory,
        double jvmUsed,
        double jvmMax,
        double uptime
) {
}

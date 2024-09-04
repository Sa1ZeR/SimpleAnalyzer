package com.sa1zer.simpleanalyzer.metricsproducer.service;

import com.sa1zer.simpleanalyzer.metricsproducer.domain.MetricReport;
import com.sa1zer.simpleanalyzer.metricsproducer.service.metric.MetricHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MetricGeneratorService {

    private final List<MetricHandler> metricHandlers;

    public MetricReport generateReport() {
        var builder = MetricReport.builder()
                .date(LocalDateTime.now());

        metricHandlers.forEach(metricHandler -> metricHandler.genetateMetricReport(builder));

        return builder.build();
    }
}

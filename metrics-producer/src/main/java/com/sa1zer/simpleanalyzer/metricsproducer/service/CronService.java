package com.sa1zer.simpleanalyzer.metricsproducer.service;

import com.sa1zer.simpleanalyzer.metricsproducer.domain.MetricReport;
import com.sa1zer.simpleanalyzer.metricsproducer.facade.MetricFacade;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.search.RequiredSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CronService {

    private final MeterRegistry meterRegistry;
    private final MetricFacade metricFacade;

    @Scheduled(fixedRate = 1000L)
    public void genMetrics() {
        metricFacade.processMetric();
    }
}

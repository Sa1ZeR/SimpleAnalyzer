package com.sa1zer.simpleanalyzer.metricsproducer.service;

import com.sa1zer.simpleanalyzer.metricsproducer.facade.MetricFacade;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CronService {

    private final MeterRegistry meterRegistry;
    private final MetricFacade metricFacade;

    @Scheduled(cron = "0 * * * * *")
    public void genMetrics() {
        metricFacade.processMetric();
    }
}

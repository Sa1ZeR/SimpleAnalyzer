package com.sa1zer.simpleanalyzer.metricsproducer.facade;

import com.sa1zer.simpleanalyzer.metricsproducer.domain.MetricReport;
import com.sa1zer.simpleanalyzer.metricsproducer.service.MetricGeneratorService;
import com.sa1zer.simpleanalyzer.metricsproducer.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MetricFacade {

    private final MetricGeneratorService metricGeneratorService;
    private final NotificationService notificationService;

    public MetricReport processMetric() {
        var metricReport = metricGeneratorService.generateReport();
        log.info("Successfully generated metric report {}", metricReport);

        notificationService.sendReport(metricReport);
        return metricReport;
    }
}

package com.sa1zer.simpleanalyzer.metricsproducer.service.metric;

import com.sa1zer.simpleanalyzer.domain.MetricReport;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UptimeMetricHandler implements MetricHandler {

    private final MeterRegistry meterRegistry;

    @Override
    public void genetateMetricReport(MetricReport.MetricReportBuilder reportBuilder) {
        var used = meterRegistry.get("process.uptime");

        var measure = used.meter().measure().iterator();
        if(measure.hasNext()) {
            var value = measure.next().getValue();
            reportBuilder.uptime(value);
        }
    }
}

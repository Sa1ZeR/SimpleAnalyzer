package com.sa1zer.simpleanalyzer.metricsproducer.service.metric;

import com.sa1zer.simpleanalyzer.metricsproducer.domain.MetricReport;
import io.micrometer.core.instrument.Measurement;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.search.RequiredSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscMetricHandler implements MetricHandler {

    private final MeterRegistry meterRegistry;

    @Override
    public void genetateMetricReport(MetricReport.MetricReportBuilder reportBuilder) {
        var search = meterRegistry.get("disk.free");

        var measure = search.meter().measure().iterator();
        if(measure.hasNext()) {
            var value = measure.next().getValue();
            reportBuilder.usableSpace(value);
        }

        var searchTotal = meterRegistry.get("disk.total");

        var measureTotal = searchTotal.meter().measure().iterator();
        if(measureTotal.hasNext()) {
            var value = measureTotal.next().getValue();
            reportBuilder.totalSpace(value);
        }
    }
}

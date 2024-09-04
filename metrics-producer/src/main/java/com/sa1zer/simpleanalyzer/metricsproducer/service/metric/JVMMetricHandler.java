package com.sa1zer.simpleanalyzer.metricsproducer.service.metric;

import com.sa1zer.simpleanalyzer.domain.MetricReport;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JVMMetricHandler implements MetricHandler {

    private final MeterRegistry meterRegistry;

    @Override
    public void genetateMetricReport(MetricReport.MetricReportBuilder reportBuilder) {
        var used = meterRegistry.get("jvm.memory.used");

        var measure = used.meter().measure().iterator();
        if(measure.hasNext()) {
            var value = measure.next().getValue();
            reportBuilder.jvmUsed(value);
        }

        var max = meterRegistry.get("jvm.memory.max");

        var measureMax = max.meter().measure().iterator();
        if(measureMax.hasNext()) {
            var value = measureMax.next().getValue();
            reportBuilder.jvmMax(value);
        }
    }
}

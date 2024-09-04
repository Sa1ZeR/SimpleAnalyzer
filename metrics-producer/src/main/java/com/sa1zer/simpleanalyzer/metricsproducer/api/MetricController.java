package com.sa1zer.simpleanalyzer.metricsproducer.api;

import com.sa1zer.simpleanalyzer.metricsproducer.domain.MetricReport;
import com.sa1zer.simpleanalyzer.metricsproducer.facade.MetricFacade;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/metrics/")
public class MetricController {

    private final MetricFacade metricFacade;

    @PostMapping("generate")
    @Operation(description = "Генерация отчета на текущий момент времени")
    public MetricReport generate() {
        return metricFacade.processMetric();
    }
}

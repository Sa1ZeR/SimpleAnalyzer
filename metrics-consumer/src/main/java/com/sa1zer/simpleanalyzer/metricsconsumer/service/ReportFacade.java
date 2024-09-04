package com.sa1zer.simpleanalyzer.metricsconsumer.service;

import com.sa1zer.simpleanalyzer.domain.MetricReport;
import com.sa1zer.simpleanalyzer.metricsconsumer.entity.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportFacade {

    private final ReportService reportService;

    public void handleReport(MetricReport report) {
        Report newReport = Report.builder()
                .dateTime(report.date())
                .uptime(report.uptime())
                .jvmUsed(report.jvmUsed())
                .jvmMax(report.jvmMax())
                .usableSpace(report.usableSpace())
                .totalSpace(report.totalSpace())
                .build();

        reportService.save(newReport);
    }
}

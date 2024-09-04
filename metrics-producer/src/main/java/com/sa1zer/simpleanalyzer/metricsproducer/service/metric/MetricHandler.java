package com.sa1zer.simpleanalyzer.metricsproducer.service.metric;


import com.sa1zer.simpleanalyzer.domain.MetricReport;

public interface MetricHandler {

    void genetateMetricReport(MetricReport.MetricReportBuilder reportBuilder);
}

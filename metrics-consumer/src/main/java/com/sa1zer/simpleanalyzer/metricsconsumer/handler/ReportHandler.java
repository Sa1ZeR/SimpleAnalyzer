package com.sa1zer.simpleanalyzer.metricsconsumer.handler;

import com.sa1zer.simpleanalyzer.metricsconsumer.payload.dto.MetricReport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportHandler {


    @KafkaHandler()
    public void handleReport(MetricReport report) {

    }
}

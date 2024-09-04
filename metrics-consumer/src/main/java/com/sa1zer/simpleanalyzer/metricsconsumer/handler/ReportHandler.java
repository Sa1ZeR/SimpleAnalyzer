package com.sa1zer.simpleanalyzer.metricsconsumer.handler;

import com.sa1zer.simpleanalyzer.domain.MetricReport;
import com.sa1zer.simpleanalyzer.metricsconsumer.service.ReportFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@KafkaListener(topics = {"${spring.kafka.topics.report-topic-name}"}, containerFactory = "concurrentKafkaJsonListenerContainerFactory")
public class ReportHandler {

    private final ReportFacade reportFacade;

    @KafkaHandler()
    public void handleReport(MetricReport report) {
        log.info("Received report {} ", report);

        reportFacade.handleReport(report);
    }
}

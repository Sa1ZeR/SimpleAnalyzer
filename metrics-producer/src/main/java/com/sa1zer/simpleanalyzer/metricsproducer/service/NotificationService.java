package com.sa1zer.simpleanalyzer.metricsproducer.service;

import com.sa1zer.simpleanalyzer.metricsproducer.domain.MetricReport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private KafkaTemplate<LocalDateTime, Object> kafkaTemplate;

    @Value("${spring.kafka.producer.report-topic-name}")
    private String reportTopic;

    public void sendReport(MetricReport report) {
        try {
            var result = kafkaTemplate.send(reportTopic, report.date(), report).get();
            log.info("Successfully sent report data {}-{}", result.getRecordMetadata().topic(), result.getRecordMetadata().partition());
        } catch (InterruptedException | ExecutionException e) {
            log.error("Can't send report data: ", e);
        }
    }
}

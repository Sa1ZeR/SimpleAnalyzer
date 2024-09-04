package com.sa1zer.simpleanalyzer.metricsconsumer.config.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConsumerProperty {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    public String bootstrapServers;
    @Value("${spring.kafka.topics.report-topic-name}")
    public String reportTopicName;
    @Value("${spring.kafka.consumer.group-id}")
    public String groupId;

    @Value("${spring.kafka.consumer.trust-packages}")
    public String trustedPackages;
}

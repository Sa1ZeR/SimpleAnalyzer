package com.sa1zer.simpleanalyzer.metricsproducer.config;

import com.sa1zer.simpleanalyzer.metricsproducer.config.property.ProducerProperty;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    private final ProducerProperty producerProperty;

    @Bean
    public NewTopic reportTopic() {
        return TopicBuilder.name(producerProperty.reportTopic)
                .partitions(1)
                .replicas(Integer.parseInt(producerProperty.syncReplicas))
                .configs(Map.of("min.insync.replicas", String.valueOf(Math.max(Integer.parseInt(producerProperty.syncReplicas)-1, 1)))) //число реплик, которые должны быть синхронизированы
                .build();
    }

    @Bean
    public <K, V> ProducerFactory<K, V> producerFactory() {
        Map<String, Object> map = new HashMap<>();
        ;
        map.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, producerProperty.bootstrapServers);
        map.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, producerProperty.deserializationKeyClass);
        map.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, producerProperty.deserializationValueClass);
        map.put(ProducerConfig.ACKS_CONFIG, producerProperty.acks);

        return new DefaultKafkaProducerFactory<>(map);
    }
}

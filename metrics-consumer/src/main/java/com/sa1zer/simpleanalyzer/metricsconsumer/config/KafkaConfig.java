package com.sa1zer.simpleanalyzer.metricsconsumer.config;

import com.sa1zer.simpleanalyzer.metricsconsumer.config.property.ConsumerProperty;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.backoff.FixedBackOff;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    private final ConsumerProperty consumerProperty;

    @Bean
    public ConsumerFactory<Long, Object> consumerFactory() {
        Map<String, Object> map = new HashMap<>();

        map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, consumerProperty.bootstrapServers);
        map.put(ConsumerConfig.GROUP_ID_CONFIG, consumerProperty.groupId); //указывается, если будет запущено несколько инстансов. Партиции будут равномерно распределены
        map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class); //если не найдет как сериализовать класс, то кинет ошибку и не будет пытаться повторно его десериализовать
        map.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, ErrorHandlingDeserializer.class);
        map.put(JsonDeserializer.TRUSTED_PACKAGES, consumerProperty.trustedPackages); //небезопасно, надо указывать явно путь до наших классов =)

        return new DefaultKafkaConsumerFactory<>(map);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Long, Object> concurrentKafkaJsonListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Long, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        return factory;
    }

//    @Bean
//    public CommonErrorHandler errorHandler(KafkaOperations<Object, Object> kafkaOperations) {
//        return new DefaultErrorHandler(new DeadLetterPublishingRecoverer(kafkaOperations), new FixedBackOff(1000L, 3));
//    }
}

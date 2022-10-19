package com.mmg;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    public NewTopic topic() {
        return TopicBuilder.name("wikimedia-events")
                .partitions(1)
                .replicas(1)
                .build();
    }
}

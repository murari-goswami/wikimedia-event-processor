package com.mmg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDbConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDbConsumer.class);

    @KafkaListener(
            topics = "wikimedia-events",
            groupId = "wmGroup"
    )
    private void consume(String eventMessage) {

        LOGGER.info(String.format("event message received %s", eventMessage));


    }

}

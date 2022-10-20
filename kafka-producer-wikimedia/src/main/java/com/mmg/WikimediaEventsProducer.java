package com.mmg;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaEventsProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaEventsProducer.class);
    private static final String WIKIMEDIA_URL = "https://stream.wikimedia.org/v2/stream/recentchange";
    private KafkaTemplate<String, String> kafkaTemplate;

    public WikimediaEventsProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {
        String topic = "wikimedia-events";

        // To read realtime stream data from wikimedia, we use event source
        EventHandler eventHandler = new WikimediaEventsHandler(kafkaTemplate, topic);
        String url = WIKIMEDIA_URL;
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
        EventSource eventSource = builder.build();
        eventSource.start();

        TimeUnit.MINUTES.sleep(10);

    }

}

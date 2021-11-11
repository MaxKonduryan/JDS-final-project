package ru.jds.reboot.server.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import ru.jds.reboot.server.model.Card;
import ru.jds.reboot.server.model.ProcessingServer;

import java.util.concurrent.atomic.AtomicReference;

@Configuration
@EnableKafka
@Slf4j
public class KafkaConfig {

    private AtomicReference<ProcessingServer> processing;

    @Autowired
    public KafkaConfig(ProcessingServer processing) {
        this.processing = new AtomicReference<>(processing);
    }

    @KafkaListener(topics = "${spring.kafka.topic}")
    public void msgListener(@Payload Card card) {
        log.info("Block card: {}", card);
        processing.get().blockCard(card);
    }
}

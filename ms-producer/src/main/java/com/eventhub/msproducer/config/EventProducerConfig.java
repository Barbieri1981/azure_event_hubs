package com.eventhub.msproducer.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

import static com.eventhub.msproducer.constant.Constant.LOG_ERROR_MESSAGE;
import static com.eventhub.msproducer.constant.Constant.LOG_SEND_MESSAGE;


@Slf4j
@Configuration
public class EventProducerConfig {

    @Bean
    public Sinks.Many<Message<String>> many() {
        return Sinks.many().unicast().onBackpressureBuffer();
    }

    @Bean
    public Supplier<Flux<Message<String>>> supply(Sinks.Many<Message<String>> many) {
        return () -> many.asFlux()
                .doOnNext(m -> log.info(LOG_SEND_MESSAGE, m))
                .doOnError(t -> log.error(LOG_ERROR_MESSAGE, t));
    }
}

package com.eventhub.msproducer.config;

import com.eventhub.msproducer.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;


@Slf4j
@Configuration
public class EventProducerConfig {

    /**
     * Sinks is a producer and a subscriber
     * Returns the handler which pushes events
     * @return handler which pushes events {@link Sinks}
     */
    @Bean
    public Sinks.Many<Message<String>> sinksMany() {
        return Sinks.many().unicast().onBackpressureBuffer();
    }

    /**
     * Sinks is a producer and subscriber
     * Returns the handler which receives events
     * @param sinksMany the handler
     * @return the handler which receives events {@link Flux}
     */
    @Bean
    public Supplier<Flux<Message<String>>> producer(final Sinks.Many<Message<String>> sinksMany) {
        return () -> sinksMany.asFlux()
                .doOnNext(Util.onNext())
                .doOnError(Util.onError());
    }

}

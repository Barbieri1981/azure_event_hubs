package com.eventhub.msconsumer.config;

import com.azure.spring.messaging.checkpoint.Checkpointer;
import com.eventhub.msconsumer.constant.Constant;
import com.eventhub.msconsumer.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

import static com.azure.spring.messaging.AzureHeaders.CHECKPOINTER;

@Slf4j
@Configuration
public class EventConsumerConfig {

    private static void consumeEvents(final Message<String> event) {
        final Checkpointer checkpointer = (Checkpointer) event.getHeaders().get(CHECKPOINTER);
        log.info(Constant.LOG_CONSUME_MESSAGE, event.getPayload());
        checkpointer.success()
                .doOnSuccess(Util.onNext(event))
                .doOnError(Util.onError())
                .block();
    }

    @Bean
    public Consumer<Message<String>> consume() {
        return EventConsumerConfig::consumeEvents;
    }
}

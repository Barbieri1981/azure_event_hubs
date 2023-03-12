package com.eventhub.msproducer.controller;

import com.eventhub.msproducer.config.EventHubConfig;
import com.eventhub.msproducer.dto.Subscription;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Sinks;

import javax.validation.Valid;

@Slf4j
@RestController
public class EventProducerController {

    @Autowired
    private Sinks.Many<Message<String>> sinksMany;

    @Autowired
    private StreamBridge streamBridge;

    @Autowired
    private EventHubConfig eventHubConfig;


    @PostMapping("/event")
    public ResponseEntity<String> processEvents(final @RequestParam String req) {
        this.sinksMany.emitNext(MessageBuilder.withPayload(req).build(), Sinks.EmitFailureHandler.FAIL_FAST);
        return ResponseEntity.ok(req);
    }

    @PostMapping("/subscription")
    public ResponseEntity<Subscription> processSubscription(final @RequestBody @Valid Subscription req) {
        this.streamBridge.send(this.eventHubConfig.getBindingName(), req);
        return ResponseEntity.ok(req);
    }
}


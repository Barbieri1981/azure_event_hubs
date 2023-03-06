package com.eventhub.msproducer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Sinks;

import static com.eventhub.msproducer.constant.Constant.STREAM_BINDING_DESTINATION;

@Slf4j
@RestController
public class EventProducerController {

    @Autowired
    private Sinks.Many<Message<String>> many;

    @Autowired
    private StreamBridge streamBridge;


    @PostMapping("/messages/reactive")
    public ResponseEntity<String> sendDataReactive(final @RequestParam String request) {
        this.many.emitNext(MessageBuilder.withPayload(request).build(), Sinks.EmitFailureHandler.FAIL_FAST);
        return ResponseEntity.ok(request);
    }

    @PostMapping("/messages/imperative")
    public ResponseEntity<String> sendDataImperative(final @RequestParam String request) {
        this.streamBridge.send(STREAM_BINDING_DESTINATION, request);
        return ResponseEntity.ok(request);
    }
}


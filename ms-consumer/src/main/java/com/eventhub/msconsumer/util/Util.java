package com.eventhub.msconsumer.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Slf4j
public final class Util {

    public static Consumer<? super Void> onNext(final Message<String> message ){
        return success -> log.info("Event '{}' processed", message.getPayload());
    }

    public static Consumer<Throwable> onError(){
        return error -> log.error("Exception: ", error);
    }


    private Util() {}
}

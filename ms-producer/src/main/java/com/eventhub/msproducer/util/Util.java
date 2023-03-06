package com.eventhub.msproducer.util;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

import static com.eventhub.msproducer.constant.Constant.LOG_ERROR_MESSAGE;
import static com.eventhub.msproducer.constant.Constant.LOG_SEND_MESSAGE;

@Slf4j
public final class Util {

    public static Consumer<Object> onNext(){
        return m -> log.info(LOG_SEND_MESSAGE, m);
    }

    public static Consumer<Throwable> onError(){
        return t -> log.error(LOG_ERROR_MESSAGE, t);
    }

    private Util() {}
}

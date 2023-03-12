package com.eventhub.msproducer.util;

import com.eventhub.msproducer.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import java.util.function.Consumer;

@Slf4j
public final class Util {

    public static Consumer<? super Message<String>> onNext(){
        return m -> log.info(Constant.LOG_SEND_MESSAGE, m);
    }

    public static Consumer<Throwable> onError(){
        return t -> log.error(Constant.LOG_ERROR_MESSAGE, t);
    }

    private Util() {}
}

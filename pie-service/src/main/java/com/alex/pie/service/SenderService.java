package com.alex.pie.service;


import com.alex.pie.domain.Measurement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@EnableBinding(Source.class)
@AllArgsConstructor
public class SenderService {

    private final Source source;

    public void sendMeasurement(Measurement measurement) {
        boolean status = source.output().send(MessageBuilder.withPayload(measurement).build());
        if (status)
            log.info(measurement + " was successful sent to the KAFKA(broker)");
        else
            log.warn("Unsuccessful sending measurement to the KAFKA(broker)");
    }

}

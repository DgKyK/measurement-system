package com.alex.keeper.service;

import com.alex.keeper.domain.Measurement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableBinding(Sink.class)
@MessageEndpoint
public class CheckMeasurementService {

    @Autowired
    private MeasurementService measurementService;

    @StreamListener(Sink.INPUT)
    public void getAvailableMeasurementToSave(Measurement measurement) {
        log.info("Got [Measurement] from KAFKA(broker): " + measurement);
        measurementService.saveMeasurement(measurement);
    }

}

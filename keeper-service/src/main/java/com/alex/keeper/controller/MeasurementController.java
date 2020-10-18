package com.alex.keeper.controller;

import com.alex.keeper.domain.Measurement;
import com.alex.keeper.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class MeasurementController {

    @Autowired
    private MeasurementService measurementService;

    @RequestMapping(method = RequestMethod.GET, value = "/measurements")
    public Flux<Measurement> getAllMeasurements() {
        return measurementService.getAllMeasurements();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/measurements")
    public void saveNewMeasure(@RequestBody Measurement measurement) {
        measurementService.saveMeasurement(measurement);
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "stream/measurements",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Measurement> getAllMeasurementsStream() {
        return measurementService.getAllMeasurements().delaySequence(Duration.ofSeconds(1));
    }
}

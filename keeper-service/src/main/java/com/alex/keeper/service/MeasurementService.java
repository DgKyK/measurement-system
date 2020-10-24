package com.alex.keeper.service;

import com.alex.keeper.domain.Measurement;
import com.alex.keeper.repository.MeasurementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Service
@Slf4j
public class MeasurementService {

    @Autowired
    private MeasurementRepository measurementRepository;

    public void saveMeasurement(Measurement measurement) {
        measurementRepository.save(measurement).subscribe();
        log.info("Measurement: temperature=" + measurement.getTemperature() +
                " | humidity= " + measurement.getHumidity() + " was saved");
    }

    public Flux<Measurement> getAllMeasurements(){
        log.info("GET ALL MEASUREMENTS");
        return measurementRepository.findAll();
    }

    public Flux<Measurement> findAllByLocation(String location) {
        return measurementRepository.findAllByLocationAndCreatedDateGreaterThan(location,
                LocalDateTime.now().minusMinutes(1));
    }
}

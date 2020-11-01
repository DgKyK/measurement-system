package com.alex.pie.controller;

import com.alex.pie.domain.Measurement;
import com.alex.pie.service.SenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Slf4j
@RestController
public class PieController {

    @Autowired
    private WebClient.Builder webClient;
    @Autowired
    private SenderService senderService;

    @RequestMapping(method = RequestMethod.POST,value = "/measurements")
    public void createMeasurement(@RequestBody Measurement measurement) {
        measurement.setCreatedDate(LocalDateTime.now());
        senderService.sendMeasurement(measurement);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/measurements")
    public Flux<Measurement> getAllMeasurements() {
        return webClient.build()
                .get()
                .uri("http://KEEPER-SERVICE/measurements")
                .retrieve()
                .bodyToFlux(Measurement.class);
    }
}

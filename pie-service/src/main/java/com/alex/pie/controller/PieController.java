package com.alex.pie.controller;

import com.alex.pie.domain.Measurement;
import com.alex.pie.service.SenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Slf4j
@RestController
public class PieController {

    @Autowired
    private WebClient.Builder webClient;
    @Autowired
    private SenderService senderService;

    @RequestMapping(method = RequestMethod.POST,value = "/measurements")
    public /*Mono<Measurement>*/void createMeasurement(@RequestBody Measurement measurement) {
        measurement.setCreatedDate(LocalDateTime.now());
        senderService.sendMeasurement(measurement);
//        return webClient.build()
//                .post()
//                .uri("http://KEEPER-SERVICE/measurements")
//                .body(Mono.just(measurement), Measurement.class)
//                .retrieve()
//                .bodyToMono(Measurement.class);
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

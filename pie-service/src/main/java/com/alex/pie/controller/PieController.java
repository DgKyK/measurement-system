package com.alex.pie.controller;

import com.alex.pie.domain.Measurement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@RestController
@Slf4j
public class PieController {

    @Autowired
    private WebClient.Builder webClient;
    
    @RequestMapping(method = RequestMethod.POST,value = "/measurements")
    public Mono<Measurement> createMeasurement(@RequestBody Measurement measurement) {
        measurement.setCreatedDate(LocalDateTime.now());
        log.info("####DATA-->" + measurement.getCreatedDate());
        return webClient.build()
                .post()
                .uri("http://KEEPER-SERVICE/measurements")
                .body(Mono.just(measurement), Measurement.class)
                .retrieve()
                .bodyToMono(Measurement.class);
    }
}

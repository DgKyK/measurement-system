package com.alex.keeper.repository;

import com.alex.keeper.domain.Measurement;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Repository
public interface MeasurementRepository extends ReactiveMongoRepository<Measurement, String> {
    @Tailable
    Flux<Measurement> findAllByLocationAndCreatedDateGreaterThan(String location, LocalDateTime dateTime);
}

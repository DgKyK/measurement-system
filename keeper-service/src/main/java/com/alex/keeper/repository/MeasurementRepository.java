package com.alex.keeper.repository;

import com.alex.keeper.domain.Measurement;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends ReactiveMongoRepository<Measurement, String> {

}

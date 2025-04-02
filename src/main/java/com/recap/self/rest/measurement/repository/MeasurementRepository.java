package com.recap.self.rest.measurement.repository;

import com.recap.self.rest.measurement.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

    Integer countByRainingIsTrue();

}

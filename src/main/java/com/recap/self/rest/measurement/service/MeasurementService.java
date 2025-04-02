package com.recap.self.rest.measurement.service;

import com.recap.self.rest.measurement.dto.MeasurementResponse;
import com.recap.self.rest.measurement.exception.SensorDoesNotRegisteredException;
import com.recap.self.rest.measurement.model.Measurement;
import com.recap.self.rest.measurement.model.Sensor;
import com.recap.self.rest.measurement.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository repository;

    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepository repository, SensorService sensorService) {
        this.repository = repository;
        this.sensorService = sensorService;
    }

    public List<Measurement> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Measurement save(Measurement measurement) {
        prepareMeasurementForSaving(measurement);
        return repository.save(measurement);
    }

    private void prepareMeasurementForSaving(Measurement measurement) {
        Sensor registered = findRegistered(measurement.getSensor().getName());
        measurement.setSensor(registered);
        measurement.setCreatedAt(LocalDateTime.now());
    }

    private Sensor findRegistered(String name) {
        return sensorService.findByName(name)
                .orElseThrow(() -> new SensorDoesNotRegisteredException(String.format("Sensor [%s] doesn't registered", name)));
    }

    public MeasurementResponse getRainyDaysCount() {
        return new MeasurementResponse(repository.countByRainingIsTrue());
    }
}

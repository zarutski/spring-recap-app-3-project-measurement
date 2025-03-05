package com.recap.self.rest.measurement.service;

import com.recap.self.rest.measurement.exception.SensorAlreadyExistsException;
import com.recap.self.rest.measurement.model.Sensor;
import com.recap.self.rest.measurement.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public Sensor save(Sensor sensor) {
        checkSensorExists(sensor.getName());
        return sensorRepository.save(sensor);
    }

    private void checkSensorExists(String name) {
        if (sensorRepository.findByName(name).isPresent()) {
            throw new SensorAlreadyExistsException(String.format("Sensor [%s] already exists", name));
        }
    }
}

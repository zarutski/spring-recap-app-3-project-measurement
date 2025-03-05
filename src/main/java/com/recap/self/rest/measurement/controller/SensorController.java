package com.recap.self.rest.measurement.controller;

import com.recap.self.rest.measurement.dto.SensorDTO;
import com.recap.self.rest.measurement.exception.ValidationExceptionBuilder;
import com.recap.self.rest.measurement.model.Sensor;
import com.recap.self.rest.measurement.service.SensorService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sensor")
public class SensorController {

    private final SensorService sensorService;

    private final ModelMapper mapper;

    @Autowired
    public SensorController(SensorService sensorService, ModelMapper mapper) {
        this.sensorService = sensorService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<SensorDTO> register(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw ValidationExceptionBuilder.builder().addErrors(bindingResult.getFieldErrors()).build();
        }
        Sensor persisted = sensorService.save(convertToEntity(sensorDTO));
        return new ResponseEntity<>(convertToDTO(persisted), HttpStatus.CREATED);
    }

    private Sensor convertToEntity(SensorDTO sensorDTO) {
        return mapper.map(sensorDTO, Sensor.class);
    }

    private SensorDTO convertToDTO(Sensor sensor) {
        return mapper.map(sensor, SensorDTO.class);
    }
}

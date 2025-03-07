package com.recap.self.rest.measurement.controller;

import com.recap.self.rest.measurement.dto.MeasurementDTO;
import com.recap.self.rest.measurement.dto.MeasurementResponse;
import com.recap.self.rest.measurement.exception.ValidationExceptionBuilder;
import com.recap.self.rest.measurement.model.Measurement;
import com.recap.self.rest.measurement.service.MeasurementService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;

    private final ModelMapper mapper;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper mapper) {
        this.measurementService = measurementService;
        this.mapper = mapper;
    }

    @PostMapping("/add")
    public ResponseEntity<MeasurementDTO> add(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw ValidationExceptionBuilder.builder().addErrors(bindingResult.getFieldErrors()).build();
        }
        Measurement persisted = measurementService.save(convertToEntity(measurementDTO));
        return new ResponseEntity<>(convertToDTO(persisted), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MeasurementDTO>> getMeasurements() {
        return new ResponseEntity<>(measurementService.findAll().stream()
                .map(this::convertToDTO)
                .toList(), HttpStatus.OK);
    }

    @GetMapping("/rainyDaysCount")
    public ResponseEntity<MeasurementResponse> getRainyDaysCount() {
        return new ResponseEntity<>(measurementService.getRainyDaysCount(), HttpStatus.OK);
    }

    private MeasurementDTO convertToDTO(Measurement measurement) {
        return mapper.map(measurement, MeasurementDTO.class);
    }

    private Measurement convertToEntity(MeasurementDTO measurementDTO) {
        return mapper.map(measurementDTO, Measurement.class);
    }
}

package com.recap.self.rest.measurement.dto;

public class MeasurementResponse {

    private int rainyDaysCount;

    public MeasurementResponse() {
    }

    public MeasurementResponse(int rainyDaysCount) {
        this.rainyDaysCount = rainyDaysCount;
    }

    public int getRainyDaysCount() {
        return rainyDaysCount;
    }

    public void setRainyDaysCount(int rainyDaysCount) {
        this.rainyDaysCount = rainyDaysCount;
    }
}

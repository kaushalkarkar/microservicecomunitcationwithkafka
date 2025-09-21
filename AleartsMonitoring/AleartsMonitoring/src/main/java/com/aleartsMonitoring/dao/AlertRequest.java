package com.aleartsMonitoring.dao;

import lombok.Data;

@Data
public class AlertRequest {
    private String sourceSystem;
    private String metricType;
    private double valueExceeded;
}

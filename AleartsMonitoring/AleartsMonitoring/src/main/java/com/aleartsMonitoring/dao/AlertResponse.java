package com.aleartsMonitoring.dao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlertResponse {
    private String alertId;
    private String sourceSystem;
    private String metricType;
    private double valueExceeded;
    private String aiSuggestedRootCause;
    private String aiSuggestedAction;
    private String aiCorrelationId;
}

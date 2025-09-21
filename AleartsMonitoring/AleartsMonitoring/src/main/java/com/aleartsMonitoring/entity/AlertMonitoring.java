package com.aleartsMonitoring.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class AlertMonitoring {
	 private String alertId;
	    private String sourceSystem;
	    private String metricType;
	    private double valueExceeded;
	    private String aiSuggestedRootCause;
	    private String aiSuggestedAction;
	    private String aiCorrelationId;
}

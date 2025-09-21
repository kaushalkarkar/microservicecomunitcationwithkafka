package com.aleartsMonitoring.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.aleartsMonitoring.dao.AlertRequest;
import com.aleartsMonitoring.dao.AlertResponse;
import com.aleartsMonitoring.entity.AlertMonitoring;
import com.aleartsMonitoring.repo.AlertMonitoringRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AlertService {
	
	@Autowired
    private KafkaService kafkaService;
	
	@Autowired
	private AlertMonitoringRepository repo;

	
	 @Autowired
	    private KafkaTemplate<String, String> kafkaTemplate;
	 private final ObjectMapper objectMapper = new ObjectMapper();

	 public AlertResponse processAlert(AlertRequest request) {
	        String rootCause = "Unknown issue";
	        String action = "Investigate manually";

	        // Simple AI logic (rule-based demo)
	        if ("cpu_usage".equalsIgnoreCase(request.getMetricType()) && request.getValueExceeded() > 90) {
	            rootCause = "High CPU load";
	            action = "Consider scaling up pods or optimizing queries";
	        } else if ("memory_usage".equalsIgnoreCase(request.getMetricType()) && request.getValueExceeded() > 80) {
	            rootCause = "Memory saturation";
	            action = "Restart service or increase memory allocation";
	        } else if ("disk_usage".equalsIgnoreCase(request.getMetricType()) && request.getValueExceeded() > 85) {
	            rootCause = "Disk almost full";
	            action = "Clear logs, archive old data, or extend disk space";
	        }

	        // For demo: correlation ID based on sourceSystem
	        String correlationId = "corr-" + request.getSourceSystem().hashCode();

	        AlertResponse response = AlertResponse.builder()
	                .alertId(UUID.randomUUID().toString())
	                .sourceSystem(request.getSourceSystem())
	                .metricType(request.getMetricType())
	                .valueExceeded(request.getValueExceeded())
	                .aiSuggestedRootCause(rootCause)
	                .aiSuggestedAction(action)
	                .aiCorrelationId(correlationId)
	                .build();

	        // 🔹 Publish to Kafka
	        try {
	            String json = objectMapper.writeValueAsString(response);
	            this.kafkaService.alertsupdate(json);
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        }
	        
	        AlertMonitoring entity = new AlertMonitoring();
	        entity.setAlertId(response.getAlertId());
	        entity.setSourceSystem(response.getSourceSystem());
	        entity.setMetricType(response.getMetricType());
	        entity.setValueExceeded(response.getValueExceeded());
	        entity.setAiSuggestedRootCause(response.getAiSuggestedRootCause());
	        entity.setAiSuggestedAction(response.getAiSuggestedAction());
	        entity.setAiCorrelationId(response.getAiCorrelationId());
            // data save in database 
	        repo.save(entity);
	        

	        return response;
	    }
}

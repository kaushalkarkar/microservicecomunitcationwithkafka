package com.aleartsMonitoring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aleartsMonitoring.dao.AlertRequest;
import com.aleartsMonitoring.dao.AlertResponse;
import com.aleartsMonitoring.service.AlertService;

@RestController
@RequestMapping("/Alert")
public class AlertController {
	
	@Autowired
	private AlertService alertService;

	
	 @PostMapping("insertAlert")
	    public ResponseEntity<AlertResponse> ingestAlert(@RequestBody AlertRequest request) {
	        AlertResponse response = alertService.processAlert(request);
	        return ResponseEntity.ok(response);
	    }
}

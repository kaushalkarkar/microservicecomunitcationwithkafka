package com.aleartsMonitoring.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aleartsMonitoring.entity.AlertMonitoring;

public interface AlertMonitoringRepository extends JpaRepository<AlertMonitoring, Long> {

}

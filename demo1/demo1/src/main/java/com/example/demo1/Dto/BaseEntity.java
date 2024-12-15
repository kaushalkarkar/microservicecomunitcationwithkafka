package com.example.demo1.Dto;

import java.sql.Timestamp;

import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
@MappedSuperclass
@Data
public class BaseEntity {
	@CreationTimestamp
	private Timestamp createdOn;

	private String createdBy;

	private String createdIp;

	@UpdateTimestamp
	private Timestamp modifiedOn;

	private String modifiedBy;

	private String modifiedIp;

	private Boolean isDeleted = false;

	private Boolean isActive = true;
	
	
}

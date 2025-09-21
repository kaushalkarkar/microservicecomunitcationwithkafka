package com.kafka.repo;

import java.awt.TrayIcon.MessageType;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kafka.entity.MessageConfigurationMaster;

public interface MessageConfigurationRepository extends JpaRepository<MessageConfigurationMaster, Long> {
	
	public Optional<MessageConfigurationMaster> findByTemplateType(String templateType);

	public Optional<MessageConfigurationMaster> findByTemplateId(String templateType);

	public Optional<MessageConfigurationMaster> findByMessageType(MessageType messageType);
}

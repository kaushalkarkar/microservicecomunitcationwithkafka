package com.kafka;

import java.io.IOException;
import java.util.Optional;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.StringResourceLoader;
import org.apache.velocity.runtime.resource.util.StringResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.service.EmailNotificationService;
import com.kakfa.dao.EmailNotificationMasterDao;
import com.kakfa.entity.MessageConfigurationMaster;
import com.kakfa.repo.MessageConfigurationRepository;

@Configuration
public class KafkaConfig {
	
	@Autowired
	private EmailNotificationService emaNotificationService;
	
	@Autowired
	private MessageConfigurationRepository messageConfigurationRepository;
	
	private final ObjectMapper objectMapper = new ObjectMapper();


    @KafkaListener(topics = AppConstants.alerts_UPDATE_TOPIC, groupId = AppConstants.GROUP_ID)
    public void alertsupdate(String value) {
    	
    	try {

			 

		
			  EmailNotificationMasterDao emailNotification = parseMessage(value);
				 // Find Template From Database
			Optional<MessageConfigurationMaster> configuraitonDetails = messageConfigurationRepository
					.findByTemplateType(emailNotification.getTemplateName());

			VelocityEngine velocityEngine = new VelocityEngine();
			velocityEngine.setProperty(Velocity.RESOURCE_LOADER, "string");
			velocityEngine.setProperty("resource.loader.string.class", StringResourceLoader.class.getName());
			velocityEngine.init();

			String template = configuraitonDetails.get().getTemplate();
			String test = template.replace("{$1}", emailNotification.getUserName());
			String test1 = test.replace("{$2}", emailNotification.getUserPasswordText());

			// Add template to repository
			StringResourceRepository repository = StringResourceLoader.getRepository();
			repository.putStringResource("NEW_EMAIL_PASSWORD_TEMPLATE", test1);

			// Set parameters
			VelocityContext context = new VelocityContext();
			context.put("userName", emailNotification.getUserFullName());
			context.put("userId", emailNotification.getUserName());
			context.put("password", emailNotification.getUserPasswordText());
			
			// Send the OTP/SMS using the host, username, password, mobile number
			this.emaNotificationService.sendEmail(emailNotification, configuraitonDetails, velocityEngine, context);

		} catch (Exception e) {
			   e.printStackTrace();
			
		}


     

    }
    
    private EmailNotificationMasterDao parseMessage(String rawMessage) throws IOException {
        String message = rawMessage;
        if (message.startsWith("\"") && message.endsWith("\"")) {
            message = message.substring(1, message.length() - 1);
            message = message.replace("\\\"", "\"");
        }
       
        return objectMapper.readValue(message, EmailNotificationMasterDao.class);
    }

}

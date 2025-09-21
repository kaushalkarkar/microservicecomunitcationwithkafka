package com.kafka.service;

import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

import javax.swing.text.AbstractDocument.Content;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.apache.velocity.runtime.resource.loader.StringResourceLoader;
import org.apache.velocity.runtime.resource.util.StringResourceRepository;
import org.owasp.encoder.Encode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.kakfa.dao.EmailNotificationMasterDao;
import com.kakfa.entity.MessageConfigurationMaster;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.mail.Session;


@Service
public class EmailNotificationService {
	
	@Autowired
	private Environment environment;
	
	public void sendEmail(
	        EmailNotificationMasterDao emailNotification,
	        Optional<MessageConfigurationMaster> configuraitonDetails,
	        VelocityEngine velocityEngine,
	        VelocityContext context) {

	    if (configuraitonDetails.isEmpty()) {
	        System.out.println("No configuration found for template: " + emailNotification.getTemplateName());
	        return;
	    }

	    // Create a template repository and store the template string
	    String templateBody = configuraitonDetails.get().getTemplate();
	    StringResourceRepository repo = StringResourceLoader.getRepository();
	    repo.putStringResource("template", templateBody);

	    // Merge template with context
	    StringWriter writer = new StringWriter();
	    velocityEngine.getTemplate("template").merge(context, writer);
	    String emailContent = writer.toString();

	    final String username = environment.getProperty("spring.mail.username");
        final String password = environment.getProperty("spring.mail.password"); // Or your regular Gmail password if 2-Step Verification is off

        System.out.println(username + " " + password);
	    Properties props = new Properties();
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    Session session = Session.getInstance(props, new Authenticator() {
	        @Override
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	        }
	    });
	    try {
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(username));

	        // Send to all recipients in the emailDao
	        
	        String[] recipients = emailNotification.getToList().stream().toArray(String[]::new);
	        InternetAddress[] recipientAddresses = Arrays.stream(recipients)
	                .map(email -> {
	                    try {
	                        return new InternetAddress(email);
	                    } catch (AddressException e) {
	                        e.printStackTrace();
	                        return null;
	                    }
	                })
	                .filter(Objects::nonNull)
	                .toArray(InternetAddress[]::new);
	        
	     // CC recipients from configuration
	        if (configuraitonDetails.get().getCcList() != null && !configuraitonDetails.get().getCcList().isBlank()) {
	            String[] emailcc = configuraitonDetails.get().getCcList().trim().split(",");
	            InternetAddress[] ccAddresses = Arrays.stream(emailcc)
	                    .map(cc -> {
	                        try {
	                            return new InternetAddress(cc.trim());
	                        } catch (AddressException e) {
	                            e.printStackTrace();
	                            return null;
	                        }
	                    })
	                    .filter(Objects::nonNull)
	                    .toArray(InternetAddress[]::new);
	            message.setRecipients(Message.RecipientType.CC, ccAddresses);
	        }
	        
	        message.setRecipients(Message.RecipientType.TO, recipientAddresses);

	        message.setSubject(configuraitonDetails.get().getEmailSubject());
	        message.setContent(emailContent, "text/html; charset=utf-8");

	        Transport.send(message);
	        System.out.println("Email sent successfully ");

	    } catch (MessagingException e) {
	        e.printStackTrace();
	        System.out.println("Email sending failed. Error: " + e.getMessage());
	    }
	}


}

package com.kafka.dao;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.annotations.UpdateTimestamp;

public class EmailNotificationMasterDao {

	private Long notificationId;
	private int notificationType;
	private String subject;
	private String content;
	private List<String> toList;
	private List<String> ccList;
	private List<String> bcList;
	private String from;
	private String templateName;
	private String userName;
	private String location;
	private String mobileNumber;
	private String otp;
	private String userPasswordText;
	private String userFullName;
	private String url;
	private String modelName;
	@UpdateTimestamp
	private Timestamp modifiedOn;
	private Integer modelAccuracy;
	private String message;
	private String yesApiUrl;
	private String noApiUrl;

	public Long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	public int getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(int notificationType) {
		this.notificationType = notificationType;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getToList() {
		return toList;
	}

	public void setToList(List<String> toList) {
		this.toList = toList;
	}

	public List<String> getCcList() {
		return ccList;
	}

	public void setCcList(List<String> ccList) {
		this.ccList = ccList;
	}

	public List<String> getBcList() {
		return bcList;
	}

	public void setBcList(List<String> bcList) {
		this.bcList = bcList;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getUserPasswordText() {
		return userPasswordText;
	}

	public void setUserPasswordText(String userPasswordText) {
		this.userPasswordText = userPasswordText;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public Timestamp getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public Integer getModelAccuracy() {
		return modelAccuracy;
	}

	public void setModelAccuracy(Integer modelAccuracy) {
		this.modelAccuracy = modelAccuracy;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getYesApiUrl() {
		return yesApiUrl;
	}

	public void setYesApiUrl(String yesApiUrl) {
		this.yesApiUrl = yesApiUrl;
	}

	public String getNoApiUrl() {
		return noApiUrl;
	}

	public void setNoApiUrl(String noApiUrl) {
		this.noApiUrl = noApiUrl;
	}

}
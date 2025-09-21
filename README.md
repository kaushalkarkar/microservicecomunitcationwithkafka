Microservice Communication with Kafka

This project demonstrates how two microservices communicate asynchronously using Apache Kafka.
The Producer publishes alert messages to a Kafka topic, and the Consumer listens to these messages to perform further actions such as sending email, SMS, push notifications, or logging.


📌 Architecture Overview

Producer (Alerts Monitoring Service)
Publishes alert updates to a Kafka topic.

Consumer (Notification Service)
Listens to the topic and processes incoming messages.

Producer Configuration(alertsMonitoring)

1).KafkaConfig.java 
@Bean 
public NewTopic topic() 
{ 
return TopicBuilder .name(AppConstants.alert_TOPIC_NAME) .build(); 
} 

2).KafkaService.java
public boolean alertsupdate(String location) 
{ 
this.kafkaTemplate.send(AppConstants.alert_TOPIC_NAME, location); 
return true; 
}

3).application.properties 
spring.kafka.producer.bootstrap-servers=localhost:9092 
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer 
spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer 

4).call consumer 
this.kafkaService.alertsupdate(json); 


Consumer Configuration 

1).KafkaConfig.java @KafkaListener(topics = AppConstants.alerts_UPDATE_TOPIC, groupId = AppConstants.GROUP_ID) 
public void alertsupdate(String value) 
{ 
// enter mail,opt,push notification logics 
} 

2).
application.properties 

spring.kafka.consumer.bootstrap-servers=localhost:9092 
spring.kafka.consumer.group-id=group-1 
spring.kafka.consumer.auto-offset-reset=earliest 
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer 
spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer 

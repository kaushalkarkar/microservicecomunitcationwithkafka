FROM openjdk:17-jdk
ENV TZ="Asia/Kolkata"
ARG APP_NAME="demo1"
ARG APP_VERSION="0.0.1-SNAPSHOT"
ARG JAR_FILE="/target/${APP_NAME}-${APP_VERSION}.jar"

COPY ${JAR_FILE} demo1.jar

ENTRYPOINT ["java","-jar", "demo1.jar"]
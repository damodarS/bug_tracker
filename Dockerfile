FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/bug-tracker-0.1.jar

WORKDIR /opt/app

# cp target/bug-tracker-0.1.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]
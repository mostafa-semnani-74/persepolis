FROM openjdk:8
MAINTAINER Mosi
#RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring
ARG JAR_FILE=target/persepolis-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} persepolis-0.0.1.jar
ENTRYPOINT ["java","-jar","/persepolis-0.0.1.jar"]

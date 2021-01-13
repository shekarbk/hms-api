FROM openjdk:11-jre-slim
VOLUME /tmp
ADD target/hms-0.0.1-SNAPSHOT.jar hms.jar
ENTRYPOINT ["java","-jar","/hms.jar"]
FROM openjdk:12

VOLUME /tmp
ENV SPRING_PROFILE cloud

ARG JAR_FILE
COPY target/*.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
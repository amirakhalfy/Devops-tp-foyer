FROM openjdk:17-jdk-alpine
WORKDIR /app
EXPOSE 8082
RUN apk add --no-cache curl
ARG NEXUS_TOKEN
RUN curl -H "Authorization: Bearer ${NEXUS_TOKEN}" -O http://192.168.50.4:8081/repository/maven-releases/tn/esprit/tp-foyer/5.0.0/tp-foyer-5.0.0.jar
ENTRYPOINT ["java", "-jar", "tp-foyer-5.0.0.jar"]

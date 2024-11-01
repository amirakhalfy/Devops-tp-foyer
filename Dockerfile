FROM openjdk:8-jdk-alpine
EXPOSE 8082
ADD target/tp-foyer-1.0.jar tp-foyer.jar
ENTRYPOINT ["java","-jar","/tp-foyer.jar"]

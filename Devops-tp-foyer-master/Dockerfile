# Use a lightweight OpenJDK base image
FROM openjdk:17-jdk-alpine


# Expose the application port
EXPOSE 8082


# Ajoutez le JAR de votre application
ADD target/tp-foyer-5.0.1.jar tp-foyer-5.0.1.jar


# Set the entrypoint to run the application
ENTRYPOINT ["java", "-jar", "/tp-foyer-5.0.1.jar"]


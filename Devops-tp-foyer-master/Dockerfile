# Use a lightweight OpenJDK base image
FROM openjdk:17-jdk-alpine

## Set environment variables for Nexus credentials as build arguments
#ARG NEXUS_USERNAME
#ARG NEXUS_PASSWORD

# Set the working directory
#WORKDIR /app

# Expose the application port
EXPOSE 8082

## Install curl to enable downloading from Nexus
#RUN apk add --no-cache curl

# Ajoutez le JAR de votre application
ADD abirZahra/tp-foyer-5.0.1.jar tp-foyer-5.0.1.jar

## Download the JAR file from Nexus using the provided credentials
#RUN curl -u ${NEXUS_USERNAME}:${NEXUS_PASSWORD} -O http://192.168.50.4:8081/repository/maven-central-repository/tn/esprit/tp-foyer/5.0.1/tp-foyer-5.0.1.jar

# Set the entrypoint to run the application
ENTRYPOINT ["java", "-jar", "tp-foyer-5.0.1.jar"]

#FROM openjdk:17-jdk-alpine

# Exposer le port de l'application
#EXPOSE 8082

# Copier tous les fichiers du projet dans le conteneur
#COPY . /app

# Spécifiez le répertoire de travail
#WORKDIR /app



# Définir le point d'entrée pour exécuter l'application
#ENTRYPOINT ["java", "-jar", "tp-foyer-5.0.0.jar"]
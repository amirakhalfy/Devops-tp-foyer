## Use a lightweight OpenJDK base image
#FROM openjdk:17-jdk-alpine
#
#
## Expose the application port
#EXPOSE 8082
#
#
## Ajoutez le JAR de votre application
#ADD target/tp-foyer-5.0.1.jar tp-foyer-5.0.1.jar
#
#
## Set the entrypoint to run the application
#ENTRYPOINT ["java", "-jar", "tp-foyer-5.0.1.jar"]
#
# Utiliser une image de base spécifique
FROM openjdk:17-jdk-slim

# Définir le répertoire de travail
WORKDIR /app

# Copier le JAR de votre application
COPY target/tp-foyer-5.0.1.jar tp-foyer-5.0.1.jar

# Exposer le port de l'application
EXPOSE 8082

# Définir l'entrypoint pour exécuter l'application
ENTRYPOINT ["java", "-jar", "tp-foyer-5.0.1.jar"]

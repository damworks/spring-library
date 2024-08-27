# Usa un'immagine base di OpenJDK
FROM --platform=linux/amd64 openjdk:17-jdk-alpine

# Imposta il working directory
WORKDIR /app

# Copia il file JAR generato nel container
COPY target/*.jar app.jar

# Esponi la porta dell'applicazione
EXPOSE 8080
EXPOSE 5005

# Comando per avviare l'applicazione Spring Boot con opzioni di debug
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "app.jar"]

FROM openjdk:17-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled Spring Boot JAR file to the working directory
COPY target/*.jar app.jar

# Expose the port on which the Spring Boot app will run
EXPOSE 8080

# Start the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]

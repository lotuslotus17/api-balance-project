# Use a base image with Maven to build the application
FROM maven:latest AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the Maven configuration files
COPY pom.xml .
COPY src ./src

# Package the application
RUN mvn clean package

# Use a base image with Java to run the application
FROM openjdk:17-oracle

# Set a working directory in the container
WORKDIR /app

# Copy the built JAR from the previous stage
COPY --from=builder /app/target/*.jar app.jar

# Entry point to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
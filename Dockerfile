# Use a base image with OpenJDK 17 installed
FROM openjdk:17

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/FlightService-0.0.1-SNAPSHOT.jar /app

# Expose the port your application runs on (change if needed)
EXPOSE 8080

# Command to run your application
CMD ["java", "-jar", "FlightService-0.0.1-SNAPSHOT.jar"]

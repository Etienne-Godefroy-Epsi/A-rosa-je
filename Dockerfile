# Use an OpenJDK Runtime as a parent image
FROM openjdk:19
# Set the working directory to /app
WORKDIR /app
# Copy the executable into the container at /app
ADD target/*.jar app.jar
# Run app.jar when the container launches
CMD ["java", "-jar", "/app/app.jar"]

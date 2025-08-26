# Use Eclipse Temurin (more secure and stable) for building
FROM eclipse-temurin:21-jdk-alpine AS build

# Set working directory
WORKDIR /app

# Install curl for health check later
RUN apk add --no-cache curl

# Copy Maven wrapper and pom.xml first for better caching
COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .
COPY .mvn .mvn

# Make Maven wrapper executable
RUN chmod +x ./mvnw

# Download dependencies (this layer will be cached if pom.xml doesn't change)
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application with verbose output to debug issues
RUN ./mvnw clean compile -DskipTests -X || (echo "Compilation failed, showing target directory:" && ls -la target/ && exit 1)
RUN ./mvnw package -DskipTests

# Use Eclipse Temurin slim for runtime
FROM eclipse-temurin:21-jre-alpine

# Set working directory
WORKDIR /app

# Install curl for health check
RUN apk add --no-cache curl

# Create a non-root user for security
RUN addgroup -g 1001 -S appuser && adduser -S appuser -G appuser

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Change ownership of the app directory to the non-root user
RUN chown -R appuser:appuser /app
USER appuser

# Expose the port the app runs on
EXPOSE 8080

# Set JVM options for containerized environment
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

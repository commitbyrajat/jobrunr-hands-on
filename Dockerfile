FROM eclipse-temurin:21-jre-alpine

# Set working directory
WORKDIR /app

# Create non-root user
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Copy JAR files from the build context
COPY enquer/target/*.jar /app/enquer.jar
COPY processor/target/*.jar /app/processor.jar

# Set environment variable with default value
ENV SERVICE_TYPE=enquer

# Create a script to select which JAR to run
RUN echo '#!/bin/sh' > /app/run.sh && \
    echo 'if [ "$SERVICE_TYPE" = "enquer" ]; then' >> /app/run.sh && \
    echo '  echo "Starting Enquer service..."' >> /app/run.sh && \
    echo '  exec java -jar /app/enquer.jar' >> /app/run.sh && \
    echo 'elif [ "$SERVICE_TYPE" = "processor" ]; then' >> /app/run.sh && \
    echo '  echo "Starting Processor service..."' >> /app/run.sh && \
    echo '  exec java -jar /app/processor.jar' >> /app/run.sh && \
    echo 'else' >> /app/run.sh && \
    echo '  echo "Unknown SERVICE_TYPE: $SERVICE_TYPE. Valid values are \"enquer\" or \"processor\""' >> /app/run.sh && \
    echo '  exit 1' >> /app/run.sh && \
    echo 'fi' >> /app/run.sh && \
    chmod +x /app/run.sh && \
    chown -R appuser:appgroup /app

# Switch to non-root user
USER appuser

# Set the entrypoint to our script
ENTRYPOINT ["/app/run.sh"]

# Default command (can be overridden)
CMD []

# Expose both 8080 (for web interface) and 8000 (for JobRunr dashboard)
EXPOSE 8080 8000
#!/bin/bash

echo "🚀 Setting up development environment..."

# Wait for MySQL to be ready
echo "⏳ Waiting for MySQL to start..."
while ! mysqladmin ping -h mysql --silent; do
    sleep 1
done

echo "✅ MySQL is ready!"

# Build the project
echo "🔨 Building the project..."
cd /workspace/app
mvn clean install -DskipTests

echo "✅ Project built successfully!"

# Start the application
echo "🚀 Starting Spring Boot application..."
mvn spring-boot:run
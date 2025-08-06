#!/bin/bash

echo "🚀 Starting development environment..."

# Wait for MySQL to be ready
echo "⏳ Waiting for MySQL database to be ready..."
until nc -z mysql 3306; do
  echo "MySQL is not ready yet. Waiting..."
  sleep 2
done

echo "✅ MySQL is ready!"

# Set environment variables for database connection
export SPRING_DATASOURCE_URL="jdbc:mysql://mysql:3306/testpath?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
export SPRING_DATASOURCE_USERNAME="root"
export SPRING_DATASOURCE_PASSWORD="root"

# Start Spring Boot application
echo "🌱 Starting Spring Boot application..."
cd /workspace/app

# Run in background and save PID
nohup mvn spring-boot:run > /workspace/app.log 2>&1 &
APP_PID=$!
echo $APP_PID > /workspace/app.pid

echo "✅ Spring Boot application started with PID: $APP_PID"
echo "📱 Application will be available at: https://YOUR_CODESPACE_NAME-8083.app.github.dev"
echo "📋 Check logs: tail -f /workspace/app.log"
echo "🛑 Stop app: kill \$(cat /workspace/app.pid)"

# Show some useful commands
echo ""
echo "🔧 Useful commands:"
echo "  - View logs: tail -f app.log"
echo "  - Restart app: ./restart-app.sh"
echo "  - Stop app: kill \$(cat app.pid)"
echo "  - Database CLI: docker-compose exec mysql mysql -u root -proot testpath"
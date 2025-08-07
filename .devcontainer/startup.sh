#!/bin/bash

echo "🚀 Starting development environment..."

# Get project root directory (parent of .devcontainer)
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_ROOT="$(dirname "$SCRIPT_DIR")"

# Wait for MySQL to be ready
echo "⏳ Waiting for MySQL database to be ready..."
for i in {1..30}; do
  if timeout 1 bash -c "</dev/tcp/mysql/3306" 2>/dev/null; then
    echo "✅ MySQL is ready!"
    break
  fi
  echo "MySQL is not ready yet. Waiting... (attempt $i/30)"
  sleep 2
done

# Continue even if MySQL check times out
echo "Proceeding with application startup..."

# Set environment variables for database connection
export SPRING_DATASOURCE_URL="jdbc:mysql://mysql:3306/testpath?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
export SPRING_DATASOURCE_USERNAME="root"
export SPRING_DATASOURCE_PASSWORD="root"

# Start Spring Boot application
echo "🌱 Starting Spring Boot application..."
cd "$PROJECT_ROOT/app"

# Run in background and save PID
nohup mvn spring-boot:run > "$PROJECT_ROOT/app.log" 2>&1 &
APP_PID=$!
echo $APP_PID > "$PROJECT_ROOT/app.pid"

echo "✅ Spring Boot application started with PID: $APP_PID"
echo "📱 Application will be available at: https://YOUR_CODESPACE_NAME-8083.app.github.dev"
echo "📋 Check logs: tail -f $PROJECT_ROOT/app.log"
echo "🛑 Stop app: kill \$(cat $PROJECT_ROOT/app.pid)"

# Show some useful commands
echo ""
echo "🔧 Useful commands:"
echo "  - View logs: tail -f app.log"
echo "  - Restart app: ./restart-app.sh"
echo "  - Stop app: kill \$(cat app.pid)"
echo "  - Database CLI: docker-compose exec mysql mysql -u root -proot testpath"
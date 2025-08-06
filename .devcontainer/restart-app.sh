#!/bin/bash

echo "🔄 Restarting Spring Boot application..."

# Get project root directory (parent of .devcontainer)
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_ROOT="$(dirname "$SCRIPT_DIR")"

# Stop existing application
if [ -f "$PROJECT_ROOT/app.pid" ]; then
  PID=$(cat "$PROJECT_ROOT/app.pid")
  if kill -0 $PID 2>/dev/null; then
    echo "🛑 Stopping existing application (PID: $PID)..."
    kill $PID
    sleep 3
  fi
  rm -f "$PROJECT_ROOT/app.pid"
fi

# Start application
echo "🌱 Starting Spring Boot application..."
cd "$PROJECT_ROOT/app"

export SPRING_DATASOURCE_URL="jdbc:mysql://mysql:3306/testpath?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
export SPRING_DATASOURCE_USERNAME="root"
export SPRING_DATASOURCE_PASSWORD="root"

nohup mvn spring-boot:run > "$PROJECT_ROOT/app.log" 2>&1 &
APP_PID=$!
echo $APP_PID > "$PROJECT_ROOT/app.pid"

echo "✅ Spring Boot application restarted with PID: $APP_PID"
echo "📋 Check logs: tail -f app.log"
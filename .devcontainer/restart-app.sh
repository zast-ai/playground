#!/bin/bash

echo "🔄 Restarting Spring Boot application..."

# Stop existing application
if [ -f "/workspace/app.pid" ]; then
  PID=$(cat /workspace/app.pid)
  if kill -0 $PID 2>/dev/null; then
    echo "🛑 Stopping existing application (PID: $PID)..."
    kill $PID
    sleep 3
  fi
  rm -f /workspace/app.pid
fi

# Start application
echo "🌱 Starting Spring Boot application..."
cd /workspace/app

export SPRING_DATASOURCE_URL="jdbc:mysql://mysql:3306/testpath?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
export SPRING_DATASOURCE_USERNAME="root"
export SPRING_DATASOURCE_PASSWORD="root"

nohup mvn spring-boot:run > /workspace/app.log 2>&1 &
APP_PID=$!
echo $APP_PID > /workspace/app.pid

echo "✅ Spring Boot application restarted with PID: $APP_PID"
echo "📋 Check logs: tail -f app.log"
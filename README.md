# playground

A demo project based on Spring Boot and MySQL, supporting both local development and cloud development with GitHub Codespaces.

## 🚀 Quick Start

### Option 1: GitHub Codespaces (Recommended)

1.  **One-Click Launch**:

    - Click "Code" -> "Codespaces" -> "Create codespace" on the repository page.
    - Or visit: `https://github.com/codespaces/new?hide_repo_select=true&ref=main&repo=YOUR_REPO`

2.  **Automatic Configuration**:

    - The system will automatically start the MySQL database.
    - It will also automatically build and launch the Spring Boot application.
    - No manual configuration is needed.

3.  **Access the Application**:
    - After the startup is complete, VS Code will prompt for port forwarding.
    - Click the pop-up link or find port 8083 in the "PORTS" tab.

### Option 2: Local Docker Development

1.  Start the application using Docker Compose:

    ```bash
    docker-compose up -d
    ```

2.  Access the application:

    - URL: http://localhost:8083

### Option 3: VS Code Dev Container

1.  Install the "Dev Containers" extension.
2.  Press `Ctrl+Shift+P` -> select "Dev Containers: Reopen in Container".
3.  The application will run automatically after the container starts.

## 🔐 Login Credentials

- Username: `admin`
- Password: `admin`

## 🛠 Development Tools

### Useful Commands

```bash
# View application logs
tail -f app.log

# Restart the application
./restart-app.sh

# Build the project manually
cd app && mvn clean install

# Start the application manually
cd app && mvn spring-boot:run

# Connect to the database
docker-compose exec mysql mysql -u root -proot testpath

# Stop the application
kill $(cat app.pid)
```

### Port Information

- **8083**: Spring Boot Application
- **3306**: MySQL Database

## 📝 Tech Stack

- **Backend**: Spring Boot 2.x, Java 8
- **Database**: MySQL 8.0
- **Security**: Spring Security
- **Build**: Maven
- **Container**: Docker & Docker Compose

## 🏗 Project Architecture

### Docker Configuration Explained

This project includes two sets of Docker configurations for different environments:

**Development Environment** (Codespaces/Dev Container):

- **Configuration**: `.devcontainer/docker-compose.yml` + `.devcontainer/Dockerfile`
- **Features**: Includes development tools, the `vscode` user, and a full Java environment.
- **Use Case**: GitHub Codespaces, VS Code Dev Container.

**Production Environment**:

- **Configuration**: `docker-compose.yml` + `app/Dockerfile`
- **Features**: Minimalist image, multi-stage builds, runtime only.
- **Use Case**: Production deployment, quick local start.

## 🐛 Troubleshooting

For a detailed troubleshooting guide, please refer to the [Dev Container README](.devcontainer/README.md).

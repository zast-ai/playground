# GitHub Codespaces Auto-Start Configuration

This configuration supports the automatic startup of a Spring Boot project and a MySQL database in GitHub Codespaces.

## 🚀 Auto-Start Flow

1.  **Initialization Phase** - Start the MySQL database container.
2.  **Creation Phase** - Build the Maven project.
3.  **Startup Phase** - Automatically start the Spring Boot application.

## 📱 Accessing the Application

Once started, the application will be running on port 8083:

- **Local Development**: http://localhost:8083
- **Codespaces**: https://YOUR_CODESPACE_NAME-8083.app.github.dev

## 🔧 Management Commands

### Check Application Status

```bash
# View application logs
tail -f app.log

# Check process status
ps aux | grep java
```

### Restart the Application

```bash
# Use the restart script
./restart-app.sh

# Or restart manually
kill $(cat app.pid)
mvn spring-boot:run
```

### Stop the Application

```bash
kill $(cat app.pid)
```

### Database Management

```bash
# Connect to the database
docker-compose exec mysql mysql -u root -proot testpath

# View database logs
docker-compose logs mysql

# Build the project manually
cd app && mvn clean install

# Start the application manually
cd app && mvn spring-boot:run
```

## 🐛 Troubleshooting

### Codespaces Startup Errors

#### "Unable to find user vscode" Error

This error is usually due to a Docker configuration conflict:

**Solution**:

- Use a dedicated development environment configuration: `.devcontainer/docker-compose.yml`
- The development environment uses `.devcontainer/Dockerfile` (which includes the `vscode` user).
- The production environment uses `./app/Dockerfile` (a streamlined runtime environment).

#### "Starting directory does not exist" Error

If you encounter a "workspace directory does not exist" error in GitHub Codespaces:

1.  **Check Configuration Consistency**:

    - The `workspaceFolder` in `devcontainer.json` must match the volume mount path in `docker-compose.yml`.
    - Current configuration: `/workspaces/demo-playground`

2.  **Use Dynamic Path Detection in Scripts**:
    - All scripts should automatically detect the project root directory and not rely on hardcoded paths.
    - This ensures they work correctly even if the directory structure changes.

### Application Startup Failure

1.  Check if MySQL is running correctly:

    ```bash
    docker-compose ps
    ```

2.  Check the application logs:

    ```bash
    tail -f app.log
    ```

3.  Restart the services:
    ```bash
    docker-compose restart mysql
    ./restart-app.sh
    ```

### Port Access Issues

- Ensure that port 8083 is correctly forwarded in Codespaces.
- Check the "PORTS" tab in VS Code.
- It may take a few minutes for the port to become active.

### Database Connection Issues

```bash
# Check network connectivity
nc -z mysql 3306

# Reset the database
docker-compose down
docker-compose up -d mysql
```

## 📝 Environment Variables

The application uses the following database connection settings:

- **URL**: `jdbc:mysql://mysql:3306/testpath`
- **Username**: `root`
- **Password**: `root`

## 🛠 Custom Configuration

To modify the configuration, please edit:

- `.devcontainer/devcontainer.json` - Dev Container configuration
- `.devcontainer/startup.sh` - Startup script
- `app/src/main/resources/application.yml` - Spring Boot configuration

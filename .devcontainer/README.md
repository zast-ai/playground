# GitHub Codespaces 自动启动配置

本配置支持在 GitHub Codespaces 中自动启动 Spring Boot 项目和 MySQL 数据库。

## 🚀 自动启动流程

1. **初始化阶段** - 启动 MySQL 数据库容器
2. **创建阶段** - 构建 Maven 项目
3. **启动阶段** - 自动启动 Spring Boot 应用

## 📱 访问应用

启动完成后，应用将在端口 8083 上运行：

- **本地开发**: http://localhost:8083
- **Codespaces**: https://YOUR_CODESPACE_NAME-8083.app.github.dev

## 🔧 管理命令

### 查看应用状态

```bash
# 查看应用日志
tail -f app.log

# 检查进程状态
ps aux | grep java
```

### 重启应用

```bash
# 使用重启脚本
./restart-app.sh

# 或手动重启
kill $(cat app.pid)
mvn spring-boot:run
```

### 停止应用

```bash
kill $(cat app.pid)
```

### 数据库管理

```bash
# 连接数据库
docker-compose exec mysql mysql -u root -proot testpath

# 查看数据库日志
docker-compose logs mysql

# 手动构建项目
cd app && mvn clean install

# 手动启动应用
cd app && mvn spring-boot:run
```

## 🐛 故障排除

### Codespaces 启动错误

#### "Unable to find user vscode" 错误

这个错误通常是因为 Docker 配置冲突：

**解决方案**：
- 使用专门的开发环境配置：`.devcontainer/docker-compose.yml` 
- 开发环境使用 `.devcontainer/Dockerfile`（包含 vscode 用户）
- 生产环境使用 `./app/Dockerfile`（精简运行环境）

#### "Starting directory does not exist" 错误

如果在 GitHub Codespaces 中遇到工作目录不存在的错误：

1. **检查配置一致性**：
   - `devcontainer.json` 中的 `workspaceFolder` 必须与 `docker-compose.yml` 中的卷挂载路径匹配
   - 当前配置：`/workspace`

2. **脚本使用动态路径检测**：
   - 所有脚本自动检测项目根目录，不依赖硬编码路径
   - 即使目录结构改变也能正常工作

### 应用启动失败

1. 检查 MySQL 是否正常运行：

   ```bash
   docker-compose ps
   ```

2. 检查应用日志：

   ```bash
   tail -f app.log
   ```

3. 重启服务：
   ```bash
   docker-compose restart mysql
   ./restart-app.sh
   ```

### 端口访问问题

- 确保 Codespaces 正确转发了 8083 端口
- 在 VS Code 中查看 "PORTS" 标签页
- 端口可能需要几分钟才能生效

### 数据库连接问题

```bash
# 检查网络连接
nc -z mysql 3306

# 重置数据库
docker-compose down
docker-compose up -d mysql
```

## 📝 环境变量

应用使用以下数据库连接配置：

- **URL**: `jdbc:mysql://mysql:3306/testpath`
- **Username**: `root`
- **Password**: `root`

## 🛠 自定义配置

如需修改配置，请编辑：

- `.devcontainer/devcontainer.json` - Dev Container 配置
- `.devcontainer/startup.sh` - 启动脚本
- `app/src/main/resources/application.yml` - Spring Boot 配置

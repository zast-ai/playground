# playground

一个基于 Spring Boot 和 MySQL 的演示项目，支持本地开发和 GitHub Codespaces 云端开发。

## 🚀 快速开始

### 方式一：GitHub Codespaces（推荐）

1. **一键启动**：

   - 点击仓库页面的 "Code" -> "Codespaces" -> "Create codespace"
   - 或访问：`https://github.com/codespaces/new?hide_repo_select=true&ref=main&repo=YOUR_REPO`

2. **自动配置**：

   - 系统会自动启动 MySQL 数据库
   - 自动构建和启动 Spring Boot 应用
   - 无需任何手动配置

3. **访问应用**：
   - 等待启动完成后，VS Code 会提示端口转发
   - 点击弹出的链接或在 "PORTS" 标签页中找到 8083 端口

### 方式二：本地 Docker 开发

1. Start the application using Docker Compose:

```bash
docker-compose up -d
```

2. Access the application:

- URL: http://localhost:8083

### 方式三：VS Code Dev Container

1. 安装 "Dev Containers" 扩展
2. 按 `Ctrl+Shift+P` -> 选择 "Dev Containers: Reopen in Container"
3. 容器启动后应用将自动运行

## 🔐 登录信息

- Username: `admin`
- Password: `admin`

## 🛠 开发工具

### 有用的命令

```bash
# 查看应用日志
tail -f app.log

# 重启应用
./restart-app.sh

# 连接数据库
docker-compose exec mysql mysql -u root -proot testpath

# 停止应用
kill $(cat app.pid)
```

### 端口说明

- **8083**: Spring Boot 应用
- **3306**: MySQL 数据库

## 📝 技术栈

- **Backend**: Spring Boot 2.x, Java 8
- **Database**: MySQL 8.0
- **Security**: Spring Security
- **Build**: Maven
- **Container**: Docker & Docker Compose

## 🐛 故障排除

详细的故障排除指南请参考：[Dev Container README](.devcontainer/README.md)

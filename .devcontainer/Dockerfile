FROM mcr.microsoft.com/devcontainers/java:8

USER root

# Install essential development tools
RUN apt-get update && apt-get install -y \
    maven \
    git \
    curl \
    wget \
    netcat \
    mysql-client \
    && rm -rf /var/lib/apt/lists/*

# Set up Maven directory and settings
RUN mkdir -p /home/vscode/.m2 && chown vscode:vscode /home/vscode/.m2

# Copy Maven settings if available
COPY settings.xml /home/vscode/.m2/settings.xml
RUN chown -R vscode:vscode /home/vscode/.m2

# Create workspace directory structure
RUN mkdir -p /workspaces/demo-playground && \
    chown -R vscode:vscode /workspaces

# Switch to vscode user
USER vscode

# Set Java environment variables
ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
ENV PATH=$JAVA_HOME/bin:$PATH

# Set working directory
WORKDIR /workspaces/demo-playground
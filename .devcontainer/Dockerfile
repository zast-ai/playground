FROM mcr.microsoft.com/devcontainers/java:8

USER root

# Only install Maven for building the project
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/* && \
    apt-get clean

# Create workspace directory structure
RUN mkdir -p /workspaces/demo-playground && \
    chown -R vscode:vscode /workspaces

# Switch to vscode user
USER vscode

# Set environment variables
ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
ENV PATH=$JAVA_HOME/bin:/usr/share/maven/bin:$PATH

# Set working directory
WORKDIR /workspaces/demo-playground
